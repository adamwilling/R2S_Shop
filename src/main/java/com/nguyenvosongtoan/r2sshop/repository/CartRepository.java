package com.nguyenvosongtoan.r2sshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenvosongtoan.r2sshop.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}