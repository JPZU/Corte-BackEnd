package com.arapos.corte.domain.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemClothDTO {

    private int itemClothId;

    @NotNull(message = "meters is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal meters;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private OpDTO op;

    private ClothDTO cloth;

    public ItemClothDTO() {}

    public ClothDTO getCloth() {
        return cloth;
    }

    public void setCloth(ClothDTO cloth) {
        this.cloth = cloth;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getItemClothId() {
        return itemClothId;
    }

    public void setItemClothId(int itemClothId) {
        this.itemClothId = itemClothId;
    }

    public BigDecimal getMeters() {
        return meters;
    }

    public void setMeters(BigDecimal meters) {
        this.meters = meters;
    }

    public OpDTO getOp() {
        return op;
    }

    public void setOp(OpDTO op) {
        this.op = op;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
