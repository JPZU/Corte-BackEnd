package com.arapos.corte.domain.dto;

import java.time.LocalDateTime;

public class ItemReferenceDTO {

    private int itemReferenceId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private OpDTO op;

    private ReferenceDTO reference;

    public ItemReferenceDTO() {}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getItemReferenceId() {
        return itemReferenceId;
    }

    public void setItemReferenceId(int itemReferenceId) {
        this.itemReferenceId = itemReferenceId;
    }

    public OpDTO getOp() {
        return op;
    }

    public void setOp(OpDTO op) {
        this.op = op;
    }

    public ReferenceDTO getReference() {
        return reference;
    }

    public void setReference(ReferenceDTO reference) {
        this.reference = reference;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
