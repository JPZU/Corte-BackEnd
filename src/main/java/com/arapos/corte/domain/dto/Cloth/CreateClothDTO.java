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

    @NotBlank(message = "Color cannot be blank")
    @Size(max = 50, message = "Color must not exceed 50 character")
    private String color;

    @NotNull(message = "meters is required")
    @DecimalMin(value = "0.01", message = "Meters must be greater than zero")
    private BigDecimal meters;

    @NotNull(message = "isActive is required")
    private Boolean isActive;

    @Size(max = 255, message = "Notes must not exceed 255 character")
    private String notes;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private int price;

    @NotBlank(message = "Supplier invoice is required")
    @Size(max = 255, message = "Supplier invoice must not exceed 255 character")
    private String supplierInvoice;
    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    @NotNull(message = "User ID is required")
    private int userId;

    @NotNull(message = "Category ID is required")
    private int categoryId;

    @NotNull(message = "Supplier ID is required")
    private String supplierId;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public CreateClothDTO() {}
    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color.toLowerCase();
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes.toLowerCase();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSupplierInvoice() {
        return supplierInvoice;
    }

    public void setSupplierInvoice(String supplierInvoice) {
        this.supplierInvoice = supplierInvoice.toLowerCase();
    }

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
