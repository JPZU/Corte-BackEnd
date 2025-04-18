package com.arapos.corte.domain.dto.Reference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateReferenceDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    @NotBlank(message = "Reference id cannot be blank")
    @Size(max = 50, message = "Reference id must not exceed 50 character")
    private String referenceId;

    @Size(max = 255, message = "Description must not exceed 255 character")
    private String description;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public CreateReferenceDTO() {}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */
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

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
}
