package com.arapos.corte.persistence.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String supplierId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;

    //    relationship suppliers with cloths: 0..*
    @OneToMany(mappedBy = "supplier")
    private List<Cloth> clothsList;
}
