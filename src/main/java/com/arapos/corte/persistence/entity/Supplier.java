package com.arapos.corte.persistence.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @Column(name = "id", nullable = false, length = 11)
    private String supplierId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    //    relationship suppliers with cloths: 0..*
    @OneToMany(mappedBy = "supplier")
    private List<Cloth> clothsList;

    public Supplier() {}

    public List<Cloth> getClothsList() {
        return clothsList;
    }

    public void setClothsList(List<Cloth> clothsList) {
        this.clothsList = clothsList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
