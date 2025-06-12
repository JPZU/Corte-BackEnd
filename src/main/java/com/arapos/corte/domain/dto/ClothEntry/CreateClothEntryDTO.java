package com.arapos.corte.domain.dto.ClothEntry;
import jakarta.validation.constraints.*;

public class CreateClothEntryDTO {
    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int clothEntryId;

    @NotBlank(message = "Supplier Invoice cannot be blank")
    @Size(max = 255, message = "Supplier Invoice must not exceed 50 character")
    private String supplierInvoice;

    @Size(max = 255, message = "notes must not exceed 50 character")
    private String notes;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */

    @NotNull(message = "Supplier ID is required")
    private String supplierId;

    @NotNull(message = "User ID is required")
    private int userId;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public CreateClothEntryDTO(){}
    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public int getClothEntryId(){
        return clothEntryId;
    }

    public void setClothEntryId(int clothEntryId){
        this.clothEntryId = clothEntryId;
    }

    public String getSupplierInvoice(){
        return supplierInvoice;
    }

    public void setSupplierInvoice(String supplierInvoice){
        this.supplierInvoice = supplierInvoice;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
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

}
