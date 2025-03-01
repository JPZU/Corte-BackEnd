package com.arapos.corte.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class ItemReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int itemReferenceId;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;
}
