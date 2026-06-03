package com.autofinance.pricingengine.dto;

import java.math.BigDecimal;

public record VehicleEstimateResponse(
        String vin,
        String vehicleType,
        String make,
        String model,
        String modelYear,
        String trim,
        BigDecimal msrp,
        String image,
        BigDecimal monthlyPay,
        BigDecimal totalPay,
        BigDecimal rate) {
}
