package com.arapos.corte.domain.dto.ClothEntryItem;
import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public class CreateClothEntryItemDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int clothEntryItemId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must not exceed 50 character")
    private String name;

    @NotBlank(message = "Color cannot be blank")
    @Size(max = 50, message = "Color must not exceed 50 character")
    private String color;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private int price;

    @NotNull(message = "meters is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal metersAdded;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    @NotNull(message = "Category ID is required")
    private int categoryId;

    @NotNull(message = "Cloth Entry ID is required")
    private int clothEntryId;

    @NotNull(message = "Cloth ID is required")
    private int clothId;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public CreateClothEntryItemDTO(){}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public int getClothEntryItemId(){
        return clothEntryItemId;
    }

    public void setClothEntryItemId(int clothEntryItemId){
        this.clothEntryItemId = clothEntryItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color.toLowerCase();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BigDecimal getMetersAdded() {
        return metersAdded;
    }

    public void setMetersAdded(BigDecimal metersAdded) {
        this.metersAdded = metersAdded;
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

    public int getClothEntryId() {
        return clothEntryId;
    }

    public void setClothEntryId(int clothEntryId) {
        this.clothEntryId = clothEntryId;
    }

    public int getClothId() {
        return clothId;
    }

    public void setClothId(int clothId) {
        this.clothId = clothId;
    }
}
