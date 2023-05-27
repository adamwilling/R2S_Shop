package com.nguyenvosongtoan.r2sshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenvosongtoan.r2sshop.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
