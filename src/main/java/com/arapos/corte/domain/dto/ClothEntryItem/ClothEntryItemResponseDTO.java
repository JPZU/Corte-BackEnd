package com.arapos.corte.domain.dto.ClothEntryItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.ClothEntry.ClothEntryResponseDTO;

public class ClothEntryItemResponseDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int clothEntryItemId;
    private String name;
    private String color;
    private int price;
    private BigDecimal metersAdded;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    private CategoryResponseDTO category;
    private ClothEntryResponseDTO clothEntry;
    private ClothResponseDTO cloth;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public ClothEntryItemResponseDTO(){}

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    public CategoryResponseDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDTO category) {
        this.category = category;
    }

    public ClothEntryResponseDTO getClothEntry() {
        return clothEntry;
    }

    public void setClothEntry(ClothEntryResponseDTO clothEntry) {
        this.clothEntry = clothEntry;
    }

    public ClothResponseDTO getCloth() {
        return cloth;
    }

    public void setCloth(ClothResponseDTO cloth) {
        this.cloth = cloth;
    }

}
