package com.arapos.corte.persistence.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_cloths")
public class ItemCloth {

    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int itemClothId;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal meters;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    /* --------------------------------------------------------
                        RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            oneToMany
    ------------------------ */

    /* -----------------------
            manyToOne
    ------------------------ */

    //    relationship item_cloths with ops: 1
    @ManyToOne
    @JoinColumn(name = "op_id", nullable = false)
    private Op op;

    //   relationship item_cloths with cloths: 1
    @ManyToOne
    @JoinColumn(name = "cloth_id", nullable = false)
    private Cloth cloth;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public ItemCloth(){}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public int getItemClothId() {
        return itemClothId;
    }

    public void setItemClothId(int itemClothId) {
        this.itemClothId = itemClothId;
    }

    public BigDecimal getMeters() {
        return meters;
    }

    public void setMeters(BigDecimal meters) {
        this.meters = meters;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /* --------------------------------------------------------
                GETTER AND SETTER RELATIONSHIPS
    --------------------------------------------------------- */

    /* -----------------------
            oneToMany
    ------------------------ */

    /* -----------------------
            manyToOne
    ------------------------ */

    public Op getOp() {
        return op;
    }

    public void setOp(Op op) {
        this.op = op;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }
}
