package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nguyenvosongtoan.r2sshop.constant.ApiUrlConstant;
import com.nguyenvosongtoan.r2sshop.constant.PaginationConstant;
import com.nguyenvosongtoan.r2sshop.dto.ProductCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.UpdateProductDTO;
import com.nguyenvosongtoan.r2sshop.service.ProductService;

@RequestMapping(ApiUrlConstant.PRODUCTS)
@RestController
public class ProductController {

    private final ProductService productService;

    // Constructor DI để Inject ProductService
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(ApiUrlConstant.CATEGORIES)
    public ResponseEntity<?> getAllByCategoryId(
            @RequestParam long categoryId,
            @RequestParam(defaultValue = PaginationConstant.DEFAULT_PAGE_NUMBER) int no,
            @RequestParam(defaultValue = PaginationConstant.DEFAULT_PAGE_LIMIT) int limit
    ){
        try {
            return ResponseEntity.ok(productService.findAllProductByCategoryId(categoryId, no, limit));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductCreateDTO productCreateDTO){
        try {
            return ResponseEntity.ok(productService.create(productCreateDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateProductDTO updateProductDTO){
        try {
            return ResponseEntity.ok(productService.update(updateProductDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
