package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenvosongtoan.r2sshop.entity.Cart;
import com.nguyenvosongtoan.r2sshop.entity.Order;
import com.nguyenvosongtoan.r2sshop.service.CartService;
import com.nguyenvosongtoan.r2sshop.service.VariantProductService;

@RestController
@RequestMapping("/carts")
public class CartController {
	private final CartService cartService;

	public CartController(CartService cartService, VariantProductService variantProductService) {
		this.cartService = cartService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Cart> getByUserId(@PathVariable("userId") Long userId) {
		Cart cart = cartService.getByUserId(userId);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	@PostMapping("/add-product")
	public ResponseEntity<Cart> addProductToCart(@RequestBody String json) {
		return cartService.addProductToCart(json);
	}

	@PostMapping("/checkout/{cartId}")
	public ResponseEntity<Order> checkout(@PathVariable Long cardId, @RequestBody Long addressId) {
		Order order = cartService.createOrderFromCart(cardId, addressId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}