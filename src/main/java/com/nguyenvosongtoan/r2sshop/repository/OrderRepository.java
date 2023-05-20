package com.nguyenvosongtoan.r2sshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenvosongtoan.r2sshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}