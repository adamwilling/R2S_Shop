package com.nguyenvosongtoan.r2sshop.entity;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data
@Getter
@Setter
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "description")
	private String description;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<VariantProduct> variantProducts;
}
