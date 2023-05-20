package com.nguyenvosongtoan.r2sshop.entity;

import java.util.*;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Data
@Getter
@Setter
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;
}
