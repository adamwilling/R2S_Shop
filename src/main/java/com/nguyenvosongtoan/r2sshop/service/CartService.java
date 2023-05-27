package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.CartDTO;
import com.nguyenvosongtoan.r2sshop.entity.Cart;
import com.nguyenvosongtoan.r2sshop.exception.CartNotFoundException;
import com.nguyenvosongtoan.r2sshop.exception.UserNotFoundException;

public interface CartService {

	CartDTO updateCartStatus(long cartId, String status) throws CartNotFoundException;

	Cart save(Cart cart);

	List<CartDTO> getAllCartsFromLoginUser() throws UserNotFoundException;

	List<CartDTO> findByUserIdAndStatus(String status) throws UserNotFoundException;

	CartDTO findById(long cartId) throws CartNotFoundException;

}
