package com.arapos.corte.domain.dto.Op;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateOpDTO {

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

    private int userId;

    public CreateOpDTO(){}

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }
}
