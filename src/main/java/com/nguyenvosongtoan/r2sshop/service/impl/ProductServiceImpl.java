package com.nguyenvosongtoan.r2sshop.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.entity.Product;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;
import com.nguyenvosongtoan.r2sshop.exception.NotFoundException;
import com.nguyenvosongtoan.r2sshop.repository.ProductRepository;
import com.nguyenvosongtoan.r2sshop.service.ProductService;
import com.nguyenvosongtoan.r2sshop.util.PaginationSortingUtils;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Product> getProductsByCategoryId(Long categoryId, int page, int limit, String sort, String order) {
		Pageable pageable = PaginationSortingUtils.getPageable(page, limit, sort, order);
		List<Product> products = productRepository.findByCategoryId(categoryId, pageable).getContent();
		if (!products.isEmpty()) {
			return products;
		} else {
			return Collections.emptyList();
		}
	}

	@Transactional
	@Override
	public Optional<Product> getProductByIdAndCategoryId(Long productId, Long categoryId) {
		return productRepository.findByIdAndCategoryId(productId, categoryId);
	}

	@Transactional(readOnly = true)
	@Override
	public Product getById(Long key) {
		Optional<Product> Product = productRepository.findById(key);
		if (Product.isPresent()) {
			return Product.get();
		} else {
			throw new NotFoundException("Not found Product");
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Product> getAll(int page, int limit, String sort, String order) {
		Pageable pageable = PaginationSortingUtils.getPageable(page, limit, sort, order);
		List<Product> products = productRepository.findAll(pageable).getContent();
		if (!products.isEmpty()) {
			return products;
		} else {
			return Collections.emptyList();
		}
	}

	@Transactional
    @PreAuthorize("hasRole('ADMIN')")
	@Override
	public Product create(Product Product) {
		Product.setId(null);
		productRepository.save(Product);
		return Product;
	}

	@Transactional
    @PreAuthorize("hasRole('ADMIN')")
	@Override
	public Product update(Product Product) {
		Optional<Product> existedCategory = productRepository.findById(Product.getId());
		if (existedCategory.isPresent()) {
			productRepository.save(Product);
			return Product;
		} else {
			throw new NotFoundException("Not found Product");
		}
	}

	@Transactional
    @PreAuthorize("hasRole('ADMIN')")
	@Override
	public void delete(Long id) {
		Optional<Product> existedCategory = productRepository.findById(id);
		if (existedCategory.isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new NotFoundException("Not found Product");
		}
	}

	@Transactional
	@Override
	public List<VariantProduct> getVariantProductsByProductId(Long productId) {
		return productRepository.findVariantProductsById(productId);
	}

}