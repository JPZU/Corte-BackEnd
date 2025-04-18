package com.arapos.corte.domain.dto.Op;

import com.arapos.corte.domain.dto.User.UserResponseDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OpResponseDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int opId;
    private BigDecimal totalMeters;
    private int quantityCloths;
    private BigDecimal schemaLength;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
    private UserResponseDTO user;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public OpResponseDTO() {}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */
    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public BigDecimal getTotalMeters() {
        return totalMeters;
    }

    public void setTotalMeters(BigDecimal totalMeters) {
        this.totalMeters = totalMeters;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}
