package com.arapos.corte.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class SupplierDTO {

    @NotBlank(message = "Supplier id cannot be blank")
    @Size(max = 11, message = "Supplier id must not exceed 11 character")
    private String supplierId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must not exceed 50 character")
    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
