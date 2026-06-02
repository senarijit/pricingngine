package com.autofinance.pricingengine.service;

import com.autofinance.pricingengine.model.VehicleMaster;
import com.autofinance.pricingengine.repo.VehicleMasterRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoFinanceService {

    private final VehicleMasterRepository vehicleMasterRepository;

    // Constructor injection for dependency management
    public AutoFinanceService(VehicleMasterRepository vehicleMasterRepository) {
        this.vehicleMasterRepository = vehicleMasterRepository;
    }

    /**
     * Retrieves the first 10 vehicles from the database.
     * @return List of VehicleMaster records.
     */
    public List<VehicleMaster> getTop10Vehicles() {
        Pageable topTen = PageRequest.of(0, 10);
        return vehicleMasterRepository.findAll(topTen).getContent();
    }
}
