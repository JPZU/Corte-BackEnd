package com.arapos.corte.domain.dto.Op;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateOpDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int opId;
    private BigDecimal totalMeters;

    @NotNull(message = "Quantity cloths are required")
    @Positive(message = "Quantity must be greater than 0")
    private int quantityCloths;

    @NotNull(message = "schema length is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal schemaLength;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
    private int userId;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public CreateOpDTO(){}

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

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
