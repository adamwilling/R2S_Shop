package com.nguyenvosongtoan.r2sshop.entity;

import java.math.*;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="variant_product")
@Data
@Getter
@Setter
public class VariantProduct {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="size")
	private String size;
	
	@Column(name="color")
	private String color;
	
	@Column(name="material")
	private String material;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@OneToMany(mappedBy = "variantProduct", cascade = CascadeType.ALL)
	private List<CartLineItem> cartLineItems;
}
