package com.arapos.corte.persistence.entity;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "cloth_entries")
public class ClothEntry {

    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int clothEntryId;

    @Column(name = "supplier_invoice", length = 255,nullable = false)
    private String supplierInvoice;

    @Column(length = 255)
    private String notes;

        @Column(name = "created_at", insertable = false,
    updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false,
    updatable = false)
    private LocalDateTime updatedAt;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            oneToMany
    ------------------------ */

    @OneToMany(mappedBy = "clothEntry")
    private List<ClothEntryItem> clothEntryItemsList;

    /* -----------------------
            manyToOne
    ------------------------ */
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public ClothEntry(){}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public int getClothEntryId(){
        return clothEntryId;
    }

    public void setClothEntryId(int clothEntryId){
        this.clothEntryId = clothEntryId;
    }

    public String getSupplierInvoice() {
        return supplierInvoice;
    }

    public void setSupplierInvoice(String supplierInvoice) {
        this.supplierInvoice = supplierInvoice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            oneToMany
    ------------------------ */

    public List<ClothEntryItem> getClothEntryItemsList(){
        return clothEntryItemsList;
    }

    public void setClothEntryItemsList(List<ClothEntryItem> clothEntryItemsList){
        this.clothEntryItemsList = clothEntryItemsList;
    }

    /* -----------------------
            manyToOne
    ------------------------ */

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
