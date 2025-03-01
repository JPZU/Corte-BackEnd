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

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;

//    relationship: 1..*
    @OneToMany(mappedBy = "op")
    private List<ItemReference> itemReferencesList;

//    relationship: 1
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //    relationship: 1..*
    @OneToMany(mappedBy = "op")
    private List<ItemCloth> itemClothsList;

}
