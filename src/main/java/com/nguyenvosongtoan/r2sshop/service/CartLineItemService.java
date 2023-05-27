package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.AddCartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.dto.CartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.exception.CartLineItemNotFoundException;

public interface CartLineItemService {
	
	List<CartLineItemDTO> getAllByCartId(long id);

	CartLineItemDTO addProductIntoCart(AddCartLineItemDTO addCartLineItemDTO) throws Exception;

	CartLineItemDTO delProductIntoCart(long id) throws CartLineItemNotFoundException;

	void delAlLCartLineItemByCartId(long cartId);

	CartLineItemDTO updateProductInCart(CartLineItemDTO addCartLineItemDTO) throws CartLineItemNotFoundException;

	float calTotalPriceInCart(long cartId);
	
}
