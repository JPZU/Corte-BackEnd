package com.arapos.corte.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ops")
public class Op {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int opId;

    @Column(name = "total_meters", precision = 8, scale = 2, nullable = false)
    private BigDecimal totalMeters;

    @Column(name = "quantity_cloths", nullable = false)
    private int quantityCloths;

    @Column(name = "schema_length", precision = 8, scale = 2, nullable = false)
    private BigDecimal schemaLength;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

//    relationship ops with item_referencess: 1..*
    @OneToMany(mappedBy = "op")
    private List<ItemReference> itemReferencesList;

//    relationship ops with item_cloths: 1..*
    @OneToMany(mappedBy = "op")
    private List<ItemCloth> itemClothsList;

//    relationship ops with users: 1
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Op() {}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ItemCloth> getItemClothsList() {
        return itemClothsList;
    }

    public void setItemClothsList(List<ItemCloth> itemClothsList) {
        this.itemClothsList = itemClothsList;
    }

    public List<ItemReference> getItemReferencesList() {
        return itemReferencesList;
    }

    public void setItemReferencesList(List<ItemReference> itemReferencesList) {
        this.itemReferencesList = itemReferencesList;
    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public int getQuantityCloths() {
        return quantityCloths;
    }

    public void setQuantityCloths(int quantityCloths) {
        this.quantityCloths = quantityCloths;
    }

    public BigDecimal getSchemaLength() {
        return schemaLength;
    }

    public void setSchemaLength(BigDecimal schemaLength) {
        this.schemaLength = schemaLength;
    }

    public BigDecimal getTotalMeters() {
        return totalMeters;
    }

    public void setTotalMeters(BigDecimal totalMeters) {
        this.totalMeters = totalMeters;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
