package com.nguyenvosongtoan.r2sshop.entity;

import java.util.*;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="user")
@Data
@Getter
@Setter
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="fullname")
	private String fullname;

	@Column(name="email")
	private String email;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Column(name="status")
	private Boolean status;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@OneToMany(mappedBy = "user")
	private List<Address> addresses;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Cart cart;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
