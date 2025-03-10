package com.arapos.corte.domain.dto.ItemReference;

import jakarta.validation.constraints.NotNull;

public class CreateItemReferenceDTO {

    private int itemReferenceId;

    @NotNull(message = "Op ID is required")
    private int opId;

    @NotNull(message = "Reference ID is required")
    private String referenceId;

    public CreateItemReferenceDTO() {}

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public int getItemReferenceId() {
        return itemReferenceId;
    }

    public void setItemReferenceId(int itemReferenceId) {
        this.itemReferenceId = itemReferenceId;
    }
}
