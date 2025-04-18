package com.arapos.corte.domain.dto.ItemReference;

import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import com.arapos.corte.domain.dto.Reference.ReferenceResponseDTO;

import java.time.LocalDateTime;

public class ItemReferenceResponseDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int itemReferenceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
    private OpResponseDTO op;
    private ReferenceResponseDTO reference;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public ItemReferenceResponseDTO() {}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
    public OpResponseDTO getOp() {
        return op;
    }

    public void setOp(OpResponseDTO op) {
        this.op = op;
    }

    public ReferenceResponseDTO getReference() {
        return reference;
    }

    public void setReference(ReferenceResponseDTO reference) {
        this.reference = reference;
    }
}
