package com.autofinance.pricingengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "dealer_master", schema = "public")
public class DealerMaster {

    @Id
    @Column(name = "dealer_id", length = 8000, nullable = false)
    private String dealerId;

    @Column(name = "dealer_name", length = 8000)
    private String dealerName;

    @Column(name = "address_line1", length = 8000)
    private String addressLine1;

    @Column(name = "address_line2", length = 8000)
    private String addressLine2;

    @Column(name = "city", length = 8000)
    private String city;

    @Column(name = "state", length = 8000)
    private String state;

    @Column(name = "zip_code", length = 8000)
    private String zipCode;

    @Column(name = "timezone", length = 8000)
    private String timezone;

    @Column(name = "phone_number", length = 8000)
    private String phoneNumber;

    @Column(name = "email", length = 8000)
    private String email;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // Default Constructor
    public DealerMaster() {
    }

    // Getters and Setters
    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
