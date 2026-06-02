package com.autofinance.pricingengine.controller;

import com.autofinance.pricingengine.model.VehicleMaster;
import com.autofinance.pricingengine.service.AutoFinanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = " Controller", description = "Endpoints for managing auto-finance records")
public class AutoFinanceController {

    private final AutoFinanceService autoFinanceService;

    // Constructor injection for dependency management
    public AutoFinanceController(AutoFinanceService autoFinanceService) {
        this.autoFinanceService = autoFinanceService;
    }

    @GetMapping("/top10")
    @Operation(summary = "Get top 10 vehicles", description = "Fetches a limited list of the first 10 vehicle master records.")
    public ResponseEntity<List<VehicleMaster>> getTop10Vehicles() {
        List<VehicleMaster> vehicles = autoFinanceService.getTop10Vehicles();
        return ResponseEntity.ok(vehicles);
    }
}
