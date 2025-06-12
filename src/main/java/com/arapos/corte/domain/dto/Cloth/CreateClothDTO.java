package com.arapos.corte.domain.dto.Cloth;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CreateClothDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int clothId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must not exceed 50 character")
    private String name;

    @NotNull(message = "meters is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal meters;

    @NotNull(message = "isActive is required")
    private Boolean isActive;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    @NotNull(message = "Category ID is required")
    private int categoryId;


    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public CreateClothDTO() {}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public BigDecimal getMeters() {
        return meters;
    }

    public void setMeters(BigDecimal meters) {
        this.meters = meters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public int getClothId() {
        return clothId;
    }

    public void setClothId(int clothId) {
        this.clothId = clothId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
