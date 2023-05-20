package com.nguyenvosongtoan.r2sshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;

public interface VariantProductRepository extends JpaRepository<VariantProduct, Long> {
	List<VariantProduct> findVariantProductsByProductId(Long productId);
}