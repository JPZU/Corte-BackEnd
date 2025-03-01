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

    @Column(length = 255, nullable = false)
    private String description;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "reference")
    private List<ItemReference> itemReferences;

}
