package com.autofinance.pricingengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_master", schema = "public")
public class CustomerMaster {

    @Id
    @Column(name = "phone_number", length = 8000, nullable = false)
    private String phoneNumber;

    @Column(name = "first_name", length = 8000)
    private String firstName;

    @Column(name = "last_name", length = 8000)
    private String lastName;

    @Column(name = "email", length = 8000)
    private String email;

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

    @Column(name = "credit_score", length = 8000)
    private String creditScore;

    @Column(name = "credit_tier", length = 8000)
    private String creditTier;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "credit_tier_desc", length = 8000)
    private String creditTierDesc;

    // Default Constructor
    public CustomerMaster() {
    }

    // Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public String getCreditTier() {
        return creditTier;
    }

    public void setCreditTier(String creditTier) {
        this.creditTier = creditTier;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreditTierDesc() {
        return creditTierDesc;
    }

    public void setCreditTierDesc(String creditTierDesc) {
        this.creditTierDesc = creditTierDesc;
    }
}
