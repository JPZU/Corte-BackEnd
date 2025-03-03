package com.arapos.corte.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ClothDTO {

    private int clothId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must not exceed 50 character")
    private String name;

    @NotBlank(message = "Color cannot be blank")
    @Size(max = 50, message = "Color must not exceed 50 character")
    private String color;

    @NotNull(message = "meters is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal meters;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;




}
