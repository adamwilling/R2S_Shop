package com.nguyenvosongtoan.r2sshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenvosongtoan.r2sshop.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Page<Address> findByUserId(Long userId, Pageable pageable);
}