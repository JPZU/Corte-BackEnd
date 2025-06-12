package com.arapos.corte.domain.dto.Cloth;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ClothResponseDTO {

    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int clothId;
    private String name;
    private BigDecimal meters;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
    private CategoryResponseDTO category;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public ClothResponseDTO(){}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */
    public int getClothId() {
        return clothId;
    }

    public void setClothId(int clothId) {
        this.clothId = clothId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

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
        this.name = name;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
}
