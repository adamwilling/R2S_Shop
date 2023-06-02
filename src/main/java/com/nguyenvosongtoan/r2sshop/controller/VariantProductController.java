package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nguyenvosongtoan.r2sshop.constant.ApiUrlConstant;
import com.nguyenvosongtoan.r2sshop.dto.VariantProCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.VariantProductDTO;
import com.nguyenvosongtoan.r2sshop.service.VariantProductService;

import java.util.List;

@RequestMapping(ApiUrlConstant.VARIANT_PRODUCTS)
@RestController
public class VariantProductController {
    private final VariantProductService variantProductService;

    // Constructor DI để Inject VariantProductService
    public VariantProductController(VariantProductService variantProductService) {
        this.variantProductService = variantProductService;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getByProductId(@PathVariable long id) {
        try {
            return ResponseEntity.ok(variantProductService.findByProductId(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/product/{id}")
    public ResponseEntity<?> create(@PathVariable long id, @RequestBody List<VariantProCreateDTO> variantProCreateDTOs) {
        try {
            return ResponseEntity.ok(variantProductService.create(id, variantProCreateDTOs));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody List<VariantProductDTO> variantProductDTOS) {
        try {
            return ResponseEntity.ok(variantProductService.update(variantProductDTOS));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
