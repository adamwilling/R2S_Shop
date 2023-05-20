package com.nguyenvosongtoan.r2sshop.entity;

import java.util.*;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Data
@Getter
@Setter
public class Cart {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_at")
	private Date createdAt;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<CartLineItem> cartLineItems;

	public Cart() {
	}

	public Cart(User user) {
		this.user = user;
	}
}
