package com.autofinance.pricingengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "standard_rates", schema = "public")
public class RateMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "standard_rate_id")
    private Long rateId;

    @Column(name = "dealer_id", length = 100, nullable = false)
    private String dealerId;

    @Column(name = "make", length = 100, nullable = false)
    private String make;

    @Column(name = "model", length = 100, nullable = false)
    private String model;

    @Column(name = "model_year", length = 10, nullable = false)
    private String vehicleYear;

    @Column(name = "series", length = 100)
    private String trim;

    @Column(name = "term", nullable = false)
    private Integer termMonths;

    @Column(name = "type", length = 50, nullable = false)
    private String financeType;

    @Column(name = "tier", length = 50, nullable = false)
    private String creditTier;

    @Column(name = "rate", precision = 5, scale = 2, nullable = false)
    private BigDecimal rate;

    public RateMaster() {
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    public String getCreditTier() {
        return creditTier;
    }

    public void setCreditTier(String creditTier) {
        this.creditTier = creditTier;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

}
