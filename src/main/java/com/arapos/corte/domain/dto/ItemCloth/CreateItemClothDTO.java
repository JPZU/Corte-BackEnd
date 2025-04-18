package com.arapos.corte.domain.dto.ItemCloth;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateItemClothDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int itemClothId;

    @NotNull(message = "meters is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal meters;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    @NotNull(message = "Op ID is required")
    private int opId;

    @NotNull(message = "Cloth ID is required")
    private int clothId;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public CreateItemClothDTO(){}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public BigDecimal getMeters() {
        return meters;
    }

    public void setMeters(BigDecimal meters) {
        this.meters = meters;
    }

    public int getItemClothId() {
        return itemClothId;
    }

    public void setItemClothId(int itemClothId) {
        this.itemClothId = itemClothId;
    }

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    public int getClothId() {
        return clothId;
    }

    public void setClothId(int clothId) {
        this.clothId = clothId;
    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }
}
