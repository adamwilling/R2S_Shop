package com.nguyenvosongtoan.r2sshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "cart_line_item")
public class CartLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "price")
    private float price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "variant_product_id")
    private VariantProduct variantProduct;
}
