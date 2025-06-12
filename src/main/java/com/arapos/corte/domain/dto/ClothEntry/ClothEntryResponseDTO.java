package com.arapos.corte.domain.dto.ClothEntry;

import java.time.LocalDateTime;

import com.arapos.corte.domain.dto.Supplier.SupplierResponseDTO;
import com.arapos.corte.domain.dto.User.UserResponseDTO;

public class ClothEntryResponseDTO {

    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */
    private int clothEntryId;
    private String supplierInvoice;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            manyToOne
    ------------------------ */
    private UserResponseDTO user;
    private SupplierResponseDTO supplier;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */
    public ClothEntryResponseDTO(){}

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
