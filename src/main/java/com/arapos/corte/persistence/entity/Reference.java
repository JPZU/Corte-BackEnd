package com.arapos.corte.persistence.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "referencess")
public class Reference {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String referenceId;

    @Column(name = "descriptions", length = 255, nullable = false)
    private String description;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

//  relationship referencess with item_referencess: 0..*
    @OneToMany(mappedBy = "reference")
    private List<ItemReference> itemReferencesList;

    public Reference() {}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemReference> getItemReferencesList() {
        return itemReferencesList;
    }

    public void setItemReferencesList(List<ItemReference> itemReferencesList) {
        this.itemReferencesList = itemReferencesList;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
