package com.arapos.corte.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */

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

    @Column(name = "created_at", insertable = false,
    updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false,
    updatable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol role;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            oneToMany
    ------------------------ */

//    relationship users with ops: 0..*
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE})
    private List<Op> opsList;

//    relationship users with cloths: 0..*
    @OneToMany(mappedBy = "user")
    private List<Cloth> clothsList;

    /* -----------------------
            manyToOne
    ------------------------ */

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public User(){}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
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

    public List<Op> getOpsList() {
        return opsList;
    }

    public void setOpsList(List<Op> opsList) {
        this.opsList = opsList;
    }

    /* -----------------------
            manyToOne
    ------------------------ */
}
