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
}
