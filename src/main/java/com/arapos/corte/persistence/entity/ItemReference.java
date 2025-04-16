package com.arapos.corte.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "item_referencess")
public class ItemReference {

    /* --------------------------------------------------------
                            ATTRIBUTES
    --------------------------------------------------------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int itemReferenceId;

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

//    relationship item_referencess with ops: 1
    @ManyToOne
    @JoinColumn(name = "op_id", nullable = false)
    private Op op;

//   relationship item_referencess with referencess: 1
    @ManyToOne
    @JoinColumn(name = "reference_id", nullable = false)
    private Reference reference;

    /* --------------------------------------------------------
                        CONSTRUCTOR
    --------------------------------------------------------- */

    public ItemReference() {}

    /* --------------------------------------------------------
                    GETTER AND SETTER ATRIBUTES
    --------------------------------------------------------- */

    public int getItemReferenceId() {
        return itemReferenceId;
    }

    public void setItemReferenceId(int itemReferenceId) {
        this.itemReferenceId = itemReferenceId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
