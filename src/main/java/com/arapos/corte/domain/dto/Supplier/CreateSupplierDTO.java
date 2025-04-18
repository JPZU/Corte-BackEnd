package com.arapos.corte.domain.dto.Supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateSupplierDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */

    @NotBlank(message = "Supplier id cannot be blank")
    @Size(max = 11, message = "Supplier id must not exceed 11 character")
    private String supplierId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must not exceed 50 character")
    private String name;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public CreateSupplierDTO() {}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
}
