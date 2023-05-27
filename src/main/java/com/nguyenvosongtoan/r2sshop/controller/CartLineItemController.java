package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nguyenvosongtoan.r2sshop.constant.ApiUrlConstant;
import com.nguyenvosongtoan.r2sshop.dto.AddCartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.dto.CartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.service.CartLineItemService;

@RequestMapping(ApiUrlConstant.CARTLINEITEMS)
@RestController
public class CartLineItemController {
	
    private final CartLineItemService cartLineItemService;

    // Constructor DI để Inject CartLineItemService
    public CartLineItemController(CartLineItemService cartLineItemService) {
		this.cartLineItemService = cartLineItemService;
	}

	@PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/cart/{id}")
    public ResponseEntity<?> getAllCartsFromLoginUser(@PathVariable long id) {
        try {
            return ResponseEntity.ok(cartLineItemService.getAllByCartId(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping
    public ResponseEntity<?> addProductIntoCart(@RequestBody AddCartLineItemDTO addCartLineItemDTO) {
        try {
            return ResponseEntity.ok(cartLineItemService.addProductIntoCart(addCartLineItemDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping
    public ResponseEntity<?> updateProductInCart(@RequestBody CartLineItemDTO addCartLineItemDTO) {
        try {
            return ResponseEntity.ok(cartLineItemService.updateProductInCart(addCartLineItemDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delProductIntoCart(@PathVariable long id) {
        try {
            return ResponseEntity.ok(cartLineItemService.delProductIntoCart(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
