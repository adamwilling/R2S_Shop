package com.nguyenvosongtoan.r2sshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenvosongtoan.r2sshop.entity.Product;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    Optional<Product> findByIdAndCategoryId(Long id, Long categoryId);
    
    List<VariantProduct> findVariantProductsById(Long productId);
}