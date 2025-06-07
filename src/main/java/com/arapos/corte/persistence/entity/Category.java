package com.arapos.corte.persistence.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int categoryId;

    @Column(length = 50, nullable = false)
    private String name;

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

    //    relationship categories with cloths: 0..*
    @OneToMany(mappedBy = "category")
    private List<Cloth> clothsList;

    @OneToMany(mappedBy = "category")
    private List<ClothEntryItem> clothEntryItemsList;

    /* -----------------------
            manyToOne
    ------------------------ */

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public Category() {}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Cloth> getClothsList() {
        return clothsList;
    }

    public void setClothsList(List<Cloth> clothsList) {
        this.clothsList = clothsList;
    }

    public List<ClothEntryItem> getClothEntryItemsList() {
        return clothEntryItemsList;
    }

    public void setClothEntryItemsList(List<ClothEntryItem> clothEntryItemsList) {
        this.clothEntryItemsList = clothEntryItemsList;
    }

    /* -----------------------
            manyToOne
    ------------------------ */
}
