package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nguyenvosongtoan.r2sshop.constant.ApiUrlConstant;
import com.nguyenvosongtoan.r2sshop.service.CartService;

@RequestMapping(ApiUrlConstant.CARTS)
@RestController
public class CartController {
	
    private final CartService cartService;

    // Constructor DI để Inject CartService
    public CartController(CartService cartService) {
		this.cartService = cartService;
	}

    @GetMapping
    public ResponseEntity<?> getAllCartsFromLoginUser() {
        try {
            return ResponseEntity.ok(cartService.getAllCartsFromLoginUser());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(cartService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/user")
    public ResponseEntity<?> findByUserIdAndStatus(
            @RequestParam String status
    ) {
        try {
            return ResponseEntity.ok(cartService.findByUserIdAndStatus(status));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
