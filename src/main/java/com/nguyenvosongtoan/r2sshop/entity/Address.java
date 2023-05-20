package com.nguyenvosongtoan.r2sshop.entity;

import javax.persistence.*;
import lombok.*;
@Entity
@Table(name = "address")
@Data
@Getter
@Setter
public class Address {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip_code")
	private String zipCode;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
