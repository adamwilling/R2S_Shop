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
import com.nguyenvosongtoan.r2sshop.entity.Category;
import com.nguyenvosongtoan.r2sshop.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryManagementController {

	private final CategoryService categoryService;

	public CategoryManagementController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAll(
			@RequestParam(value = "page", defaultValue = PaginationConstant.DEFAULT_PAGE_NUMBER, required = false) int page,
			@RequestParam(value = "limit", defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE, required = false) int limit,
			@RequestParam(value = "sort", defaultValue = PaginationConstant.DEFAULT_SORT_DIRECTION, required = false) String sort,
			@RequestParam(value = "order", defaultValue = PaginationConstant.DEFAULT_SORT_BY, required = false) String order) {
		List<Category> categories = categoryService.getAll(page, limit, sort, order);
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") Long id) {
		Category category = categoryService.getById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category) {
		Category result = categoryService.create(category);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Category> update(@RequestBody Category category) {
		Category result = categoryService.update(category);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		categoryService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}