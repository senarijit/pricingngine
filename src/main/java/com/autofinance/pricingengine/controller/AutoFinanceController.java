package com.autofinance.pricingengine.controller;

import com.autofinance.pricingengine.dto.VehicleEstimateResponse;
import com.autofinance.pricingengine.model.VehicleMaster;
import com.autofinance.pricingengine.service.AutoFinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class AutoFinanceController {

    private final AutoFinanceService autoFinanceService;

    public AutoFinanceController(AutoFinanceService autoFinanceService) {
        this.autoFinanceService = autoFinanceService;
    }

    @GetMapping("/top10")
    public ResponseEntity<List<VehicleMaster>> getTop10Vehicles() {
        return ResponseEntity.ok(autoFinanceService.getTop10Vehicles());
    }

    @GetMapping("/estimates")
    public ResponseEntity<List<VehicleEstimateResponse>> getRetailEstimates(
            @RequestParam String phoneNumber,
            @RequestParam String zipCode) {
        var normalizedPhone = phoneNumber == null ? "" : phoneNumber.strip();
        var normalizedZip = zipCode == null ? "" : zipCode.strip();

        if (!normalizedPhone.matches("^[0-9]{10}$")) {
            throw new IllegalArgumentException("phoneNumber must be 10 digits");
        }
        if (!normalizedZip.matches("^\\d{5}(?:-\\d{4})?$")) {
            throw new IllegalArgumentException("zipCode must be a valid 5 or 9 digit postal code");
        }

        return ResponseEntity.ok(autoFinanceService.getRetailEstimates(normalizedPhone, normalizedZip));
    }
}
