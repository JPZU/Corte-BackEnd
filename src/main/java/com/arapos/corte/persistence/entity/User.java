package com.arapos.corte.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol role;

//    relationship users with ops: 0..*
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE})
    private List<Op> opsList;

//    relationship users with cloths: 0..*
    @OneToMany(mappedBy = "user")
    private List<Cloth> clothsList;

    public User() {
    }

    public List<Cloth> getClothsList() {
        return clothsList;
    }

    public void setClothsList(List<Cloth> clothsList) {
        this.clothsList = clothsList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Op> getOpsList() {
        return opsList;
    }

    public void setOpsList(List<Op> opsList) {
        this.opsList = opsList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
