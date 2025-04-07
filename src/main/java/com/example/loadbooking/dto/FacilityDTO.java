package com.example.loadbooking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class FacilityDTO {

    @NotBlank(message = "Loading point cannot be blank")
    private String loadingPoint;

    @NotBlank(message = "Unloading point cannot be blank")
    private String unloadingPoint;

    @NotNull(message = "Loading date is required")
    private Timestamp loadingDate;

    @NotNull(message = "Unloading date is required")
    private Timestamp unloadingDate;

    // Getters and setters
    public String getLoadingPoint() {
        return loadingPoint;
    }

    public void setLoadingPoint(String loadingPoint) {
        this.loadingPoint = loadingPoint;
    }

    public String getUnloadingPoint() {
        return unloadingPoint;
    }

    public void setUnloadingPoint(String unloadingPoint) {
        this.unloadingPoint = unloadingPoint;
    }

    public Timestamp getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Timestamp loadingDate) {
        this.loadingDate = loadingDate;
    }

    public Timestamp getUnloadingDate() {
        return unloadingDate;
    }

    public void setUnloadingDate(Timestamp unloadingDate) {
        this.unloadingDate = unloadingDate;
    }
}
