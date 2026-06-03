package com.autofinance.pricingengine.service;

import com.autofinance.pricingengine.dto.VehicleEstimateResponse;
import com.autofinance.pricingengine.exception.ResourceNotFoundException;
import com.autofinance.pricingengine.model.RateMaster;
import com.autofinance.pricingengine.model.VehicleMaster;
import com.autofinance.pricingengine.repo.CustomerMasterRepository;
import com.autofinance.pricingengine.repo.DealerMasterRepository;
import com.autofinance.pricingengine.repo.RateMasterRepository;
import com.autofinance.pricingengine.repo.VehicleMasterRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AutoFinanceService {

    private static final MathContext CALCULATION_CONTEXT = new MathContext(16, RoundingMode.HALF_UP);
    private static final BigDecimal MONTHS_PER_YEAR = BigDecimal.valueOf(12);

    private final CustomerMasterRepository customerMasterRepository;
    private final DealerMasterRepository dealerMasterRepository;
    private final VehicleMasterRepository vehicleMasterRepository;
    private final RateMasterRepository rateMasterRepository;

    public AutoFinanceService(CustomerMasterRepository customerMasterRepository,
                              DealerMasterRepository dealerMasterRepository,
                              VehicleMasterRepository vehicleMasterRepository,
                              RateMasterRepository rateMasterRepository) {
        this.customerMasterRepository = customerMasterRepository;
        this.dealerMasterRepository = dealerMasterRepository;
        this.vehicleMasterRepository = vehicleMasterRepository;
        this.rateMasterRepository = rateMasterRepository;
    }

    /**
     * Retrieves the first 10 vehicles from the database.
     * @return List of VehicleMaster records.
     */
    public List<VehicleMaster> getTop10Vehicles() {
        Pageable topTen = PageRequest.of(0, 10);
        return vehicleMasterRepository.findAll(topTen).getContent();
    }

    @Transactional(readOnly = true)
    public List<VehicleEstimateResponse> getRetailEstimates(String phoneNumber, String zipCode) {
        var normalizedPhone = phoneNumber.strip();
        var normalizedZip = zipCode.strip();

        var customer = customerMasterRepository.findById(normalizedPhone)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for phoneNumber", "phoneNumber", normalizedPhone));

        var creditTier = Objects.requireNonNullElse(customer.getCreditTier(), "").strip();
        if (creditTier.isBlank()) {
            throw new ResourceNotFoundException("Customer credit tier not available", "phoneNumber", normalizedPhone);
        }

        List<String> dealerIds = dealerMasterRepository.findDealerIdByZipCode(normalizedZip);
        if (dealerIds.isEmpty()) {
            throw new ResourceNotFoundException("No dealers found for zipCode", "zipCode", normalizedZip);
        }

        List<VehicleMaster> vehicles = vehicleMasterRepository.findByDealerIdIn(dealerIds);
        if (vehicles.isEmpty()) {
            throw new ResourceNotFoundException("No vehicles found for dealer zipCode", "zipCode", normalizedZip);
        }

        Map<String, List<VehicleMaster>> vehiclesByKey = vehicles.parallelStream()
                .filter(v -> v.getMake() != null && v.getModel() != null && v.getModelYear() != null)
                .collect(Collectors.groupingByConcurrent(this::buildVehicleLookupKey));

        List<RateMaster> rates = rateMasterRepository.findRetailRatesForVehicles(dealerIds, creditTier);
        if (rates.isEmpty()) {
            throw new ResourceNotFoundException("No retail rates found for the requested criteria", "creditTier", creditTier);
        }

        return rates.parallelStream()
                .flatMap(rate -> vehiclesByKey.getOrDefault(buildRateLookupKey(rate), List.<VehicleMaster>of()).stream()
                        .map(vehicle -> createEstimate(rate, vehicle)))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(VehicleEstimateResponse::monthlyPay).reversed())
                .limit(500)
                .toList();
    }

    private String buildVehicleLookupKey(VehicleMaster vehicle) {
        return String.join("|",
                normalize(vehicle.getDealerId()),
                normalize(vehicle.getMake()),
                normalize(vehicle.getModel()),
                normalize(vehicle.getModelYear()),
                normalize(vehicle.getTrim()));
    }

    private String buildRateLookupKey(RateMaster rate) {
        return String.join("|",
                normalize(rate.getDealerId()),
                normalize(rate.getMake()),
                normalize(rate.getModel()),
                normalize(rate.getVehicleYear()),
                normalize(rate.getTrim()));
    }

    private String normalize(String input) {
        return input == null ? "" : input.strip();
    }

    private VehicleEstimateResponse createEstimate(RateMaster rate, VehicleMaster vehicle) {
        var msrp = parseDecimal(vehicle.getMsrp());
        if (msrp == null || msrp.signum() <= 0 || rate.getTermMonths() <= 0) {
            return null;
        }

        var monthlyRate = rate.getRate().divide(BigDecimal.valueOf(100), CALCULATION_CONTEXT)
                .divide(MONTHS_PER_YEAR, CALCULATION_CONTEXT);
        var compoundFactor = BigDecimal.ONE.add(monthlyRate).pow(rate.getTermMonths(), CALCULATION_CONTEXT);
        var monthlyPay = msrp.multiply(monthlyRate).multiply(compoundFactor)
                .divide(compoundFactor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
        var totalPay = monthlyPay.multiply(BigDecimal.valueOf(rate.getTermMonths())).setScale(2, RoundingMode.HALF_UP);

        return new VehicleEstimateResponse(
                vehicle.getVin(),
                vehicle.getVehicleType(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getModelYear(),
                vehicle.getTrim(),
                msrp.setScale(2, RoundingMode.HALF_UP),
                vehicle.getImage(),
                monthlyPay,
                totalPay,
                rate.getRate());
    }

    private BigDecimal parseDecimal(String value) {
        if (value == null) {
            return null;
        }
        var cleaned = value.strip().replaceAll("[,$]", "");
        if (cleaned.isBlank()) {
            return null;
        }
        try {
            return new BigDecimal(cleaned);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
