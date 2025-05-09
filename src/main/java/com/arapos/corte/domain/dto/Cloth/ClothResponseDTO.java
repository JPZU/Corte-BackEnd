package com.arapos.corte.domain.dto.Cloth;

import com.arapos.corte.domain.dto.Category.CategoryResponseDTO;
import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ClothResponseDTO {

    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int clothId;
    private String name;
    private String color;
    private BigDecimal meters;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isActive;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
    private UserResponseDTO user;
    private CategoryResponseDTO category;
    private SupplierResponseDTO supplier;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public SupplierResponseDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierResponseDTO supplier) {
        this.supplier = supplier;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}
