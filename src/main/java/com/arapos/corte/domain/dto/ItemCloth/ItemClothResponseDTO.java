package com.arapos.corte.domain.dto.ItemCloth;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemClothResponseDTO {

    private int itemClothId;

    private BigDecimal meters;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private OpResponseDTO op;

    private ClothResponseDTO cloth;

    public ItemClothResponseDTO() {}

    public ClothResponseDTO getCloth() {
        return cloth;
    }

    public void setCloth(ClothResponseDTO cloth) {
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

    public OpResponseDTO getOp() {
        return op;
    }

    public void setOp(OpResponseDTO op) {
        this.op = op;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
