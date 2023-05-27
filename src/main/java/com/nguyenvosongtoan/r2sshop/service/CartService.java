package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.CartDTO;
import com.nguyenvosongtoan.r2sshop.entity.Cart;

public interface CartService {

	CartDTO updateCartStatus(long cartId, String status) throws Exception;

	Cart save(Cart cart);

	List<CartDTO> getAllCartsFromLoginUser() throws Exception;

	List<CartDTO> findByUserIdAndStatus(String status) throws Exception;

	CartDTO findById(long cartId) throws Exception;

}
