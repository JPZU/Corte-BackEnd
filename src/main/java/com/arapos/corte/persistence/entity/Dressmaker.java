package com.arapos.corte.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "dressmakers")
public class Dressmaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int dressmakerId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 11, nullable = false)
    private String phone;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;

}
