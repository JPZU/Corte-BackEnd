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

//    relationship item_referencess with ops: 1
    @ManyToOne
    @JoinColumn(name = "op_id", nullable = false)
    private Op op;

//   relationship item_referencess with referencess: 1
    @ManyToOne
    @JoinColumn(name = "reference_id", nullable = false)
    private Reference reference;
}
