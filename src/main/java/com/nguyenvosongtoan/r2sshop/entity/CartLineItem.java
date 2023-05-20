package com.nguyenvosongtoan.r2sshop.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_line_item")
@Data
@Getter
@Setter
public class CartLineItem {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "variant_product_id")
	private VariantProduct variantProduct;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	public CartLineItem(VariantProduct variantProduct, int quantity) {
		this.variantProduct = variantProduct;
		this.quantity = quantity;
		this.isDeleted = false;
	}
}
