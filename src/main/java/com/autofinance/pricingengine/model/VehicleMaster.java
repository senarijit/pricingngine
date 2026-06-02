package com.autofinance.pricingengine.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_master", schema = "public")
public class VehicleMaster {

    @Id
    @Column(name = "inventory_id", length = 8000, nullable = false)
    private String inventoryId;

    @Column(name = "dealer_id", length = 8000, nullable = false)
    private String dealerId;

    @Column(name = "vin", length = 8000)
    private String vin;

    @Column(name = "vehicle_type", length = 8000)
    private String vehicleType;

    @Column(name = "make", length = 8000)
    private String make;

    @Column(name = "model", length = 8000)
    private String model;

    @Column(name = "model_year", length = 8000)
    private String modelYear;

    @Column(name = "trim", length = 8000)
    private String trim;

    @Column(name = "miles", length = 8000)
    private String miles;

    @Column(name = "msrp", length = 8000)
    private String msrp;

    @Column(name = "color_interior", length = 8000)
    private String colorInterior;

    @Column(name = "color_exterior", length = 8000)
    private String colorExterior;

    @Column(name = "image", length = 8000)
    private String image;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // Default Constructor
    public VehicleMaster() {
    }

    // Getters and Setters
    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public String getColorInterior() {
        return colorInterior;
    }

    public void setColorInterior(String colorInterior) {
        this.colorInterior = colorInterior;
    }

    public String getColorExterior() {
        return colorExterior;
    }

    public void setColorExterior(String colorExterior) {
        this.colorExterior = colorExterior;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
