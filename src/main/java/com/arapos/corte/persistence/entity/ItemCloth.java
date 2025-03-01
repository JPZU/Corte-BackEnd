package com.arapos.corte.persistence.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_cloths")
public class ItemCloth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int itemClothId;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal meters;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;

    //    relationship item_cloths with ops: 1
    @ManyToOne
    @JoinColumn(name = "op_id", nullable = false)
    private Op op;

    //   relationship item_cloths with cloths: 1
    @ManyToOne
    @JoinColumn(name = "cloth_id", nullable = false)
    private Cloth cloth;
}
