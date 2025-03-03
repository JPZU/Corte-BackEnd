package com.arapos.corte.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ReferenceDTO {

    @NotBlank(message = "Reference id cannot be blank")
    @Size(max = 50, message = "Reference id must not exceed 50 character")
    private String referenceId;

    @Size(max = 255, message = "Description must not exceed 255 character")
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
