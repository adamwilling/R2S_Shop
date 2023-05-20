package com.nguyenvosongtoan.r2sshop.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.nguyenvosongtoan.r2sshop.entity.Address;
import com.nguyenvosongtoan.r2sshop.entity.Cart;
import com.nguyenvosongtoan.r2sshop.entity.CartLineItem;
import com.nguyenvosongtoan.r2sshop.entity.Order;
import com.nguyenvosongtoan.r2sshop.entity.User;

public interface CartService {
	Cart create(User user);

	Cart getById(Long cartId);

	Cart getByUserId(Long userId);

	ResponseEntity<Cart> addProductToCart(String json);

	BigDecimal calculateTotalPrice(Cart cart);

	Order createOrderFromCart(Long cartId, Long addressId);
}