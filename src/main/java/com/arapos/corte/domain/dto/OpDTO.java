package com.arapos.corte.domain.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OpDTO {

    private int opId;

    @NotNull(message = "Total meters is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal totalMeters;

    @NotNull(message = "Quantity cloths are required")
    @Positive(message = "Quantity must be greater than 0")
    private int quantityCloths;

    @NotNull(message = "schema length is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal schemaLength;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UserResponseDTO user;

    public OpDTO(){}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public int getQuantityCloths() {
        return quantityCloths;
    }

    public void setQuantityCloths(int quantityCloths) {
        this.quantityCloths = quantityCloths;
    }

    public BigDecimal getSchemaLength() {
        return schemaLength;
    }

    public void setSchemaLength(BigDecimal schemaLength) {
        this.schemaLength = schemaLength;
    }

    public BigDecimal getTotalMeters() {
        return totalMeters;
    }

    public void setTotalMeters(BigDecimal totalMeters) {
        this.totalMeters = totalMeters;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}
