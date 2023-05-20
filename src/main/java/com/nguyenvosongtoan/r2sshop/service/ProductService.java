package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.nguyenvosongtoan.r2sshop.entity.Product;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;

public interface ProductService extends BaseService<Product, Long> {
	List<Product> getProductsByCategoryId(Long categoryId, int page, int limit, String sort, String order);

    Optional<Product> getProductByIdAndCategoryId(Long productId, Long categoryId);

    List<VariantProduct> getVariantProductsByProductId(Long productId);
}