package com.nguyenvosongtoan.r2sshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nguyenvosongtoan.r2sshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByCategoryIdOrderByPriceDesc(@Param("categoryId") long categoryId, Pageable pageable);
}
