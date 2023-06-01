package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nguyenvosongtoan.r2sshop.constant.ApiUrlConstant;
import com.nguyenvosongtoan.r2sshop.dto.CategoryDTO;
import com.nguyenvosongtoan.r2sshop.service.CategoryService;

@RequestMapping(ApiUrlConstant.CATEGORIES)
@RestController
public class CategoryController {
	
    private final CategoryService categoryService;

    // Constructor DI để Inject CategoryService
    public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO){
        try {
            return ResponseEntity.ok(categoryService.update(categoryDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
