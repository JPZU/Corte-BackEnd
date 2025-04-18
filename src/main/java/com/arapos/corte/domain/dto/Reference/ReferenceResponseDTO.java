package com.arapos.corte.domain.dto.Reference;
import java.time.LocalDateTime;

public class ReferenceResponseDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private String referenceId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public ReferenceResponseDTO() {}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
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
}
