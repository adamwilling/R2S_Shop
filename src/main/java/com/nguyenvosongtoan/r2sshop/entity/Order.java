package com.nguyenvosongtoan.r2sshop.entity;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "order")
@Data
@Getter
@Setter
public class Order {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "created_at")
	private Date createdAt;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	public Order(User user, BigDecimal totalPrice, Date createdAt, Address address) {
		this.user = user;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.address = address;
	}
}
