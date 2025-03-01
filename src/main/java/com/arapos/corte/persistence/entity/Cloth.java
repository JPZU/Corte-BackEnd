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

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;

    //    relationship cloths with item_cloths: 0..*
    @OneToMany(mappedBy = "cloth")
    private List<ItemCloth> itemClothsList;

    //    relationship cloths with users: 1
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //    relationship cloths with categories: 1
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //    relationship cloths with suppliers: 1
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
