package com.nguyenvosongtoan.r2sshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenvosongtoan.r2sshop.constant.PaginationConstant;
import com.nguyenvosongtoan.r2sshop.entity.Product;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;
import com.nguyenvosongtoan.r2sshop.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductManagementController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAll(
			@RequestParam(value = "page", defaultValue = PaginationConstant.DEFAULT_PAGE_NUMBER, required = false) int page,
			@RequestParam(value = "limit", defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE, required = false) int limit,
			@RequestParam(value = "sort", defaultValue = PaginationConstant.DEFAULT_SORT_DIRECTION, required = false) String sort,
			@RequestParam(value = "sortBy", defaultValue = PaginationConstant.DEFAULT_SORT_BY, required = false) String order) {
		List<Product> products = productService.getAll(page, limit, sort, order);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/categories/{productId}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long productId,
			@RequestParam(value = "page", defaultValue = PaginationConstant.DEFAULT_PAGE_NUMBER, required = false) int page,
			@RequestParam(value = "limit", defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE, required = false) int limit,
			@RequestParam(value = "sort", defaultValue = PaginationConstant.DEFAULT_SORT_DIRECTION, required = false) String sort,
			@RequestParam(value = "order", defaultValue = PaginationConstant.DEFAULT_SORT_BY, required = false) String order) {
		List<Product> products = productService.getProductsByCategoryId(productId, page, limit, sort, order);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") Long id) {
		Product product = productService.getById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		Product result = productService.create(product);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Product> update(@RequestBody Product product) {
		Product result = productService.update(product);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{id}/variants")
	public ResponseEntity<List<VariantProduct>> getVariantProductsByProductId(@PathVariable Long id) {
		List<VariantProduct> variantProducts = productService.getVariantProductsByProductId(id);
		return new ResponseEntity<>(variantProducts, HttpStatus.OK);
	}
}