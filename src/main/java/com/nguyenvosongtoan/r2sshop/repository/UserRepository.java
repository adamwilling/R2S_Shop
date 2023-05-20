package com.nguyenvosongtoan.r2sshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenvosongtoan.r2sshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String username);
}