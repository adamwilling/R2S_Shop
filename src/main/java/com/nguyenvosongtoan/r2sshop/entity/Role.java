package com.nguyenvosongtoan.r2sshop.entity;

import java.util.*;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Data
@Getter
@Setter
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
