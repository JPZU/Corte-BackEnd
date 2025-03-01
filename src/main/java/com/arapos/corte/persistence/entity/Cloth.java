package com.arapos.corte.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cloths")
public class Cloth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int clothId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String color;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal meters;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    //    relationship cloths with item_cloths: 0..*
    @OneToMany(mappedBy = "cloth")
    private List<ItemCloth> itemClothsList;

    //    relationship cloths with users: 1
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //    relationship cloths with categories: 1
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    //    relationship cloths with suppliers: 1
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public Cloth(){}

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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

    public List<ItemCloth> getItemClothsList() {
        return itemClothsList;
    }

    public void setItemClothsList(List<ItemCloth> itemClothsList) {
        this.itemClothsList = itemClothsList;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
