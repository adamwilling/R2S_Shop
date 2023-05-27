package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.AddCartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.dto.CartLineItemDTO;

public interface CartLineItemService {
	
	List<CartLineItemDTO> getAllByCartId(long id);

	CartLineItemDTO addProductIntoCart(AddCartLineItemDTO addCartLineItemDTO) throws Exception;

	CartLineItemDTO delProductIntoCart(long id) throws Exception;

	void delAlLCartLineItemByCartId(long cartId);

	CartLineItemDTO updateProductInCart(CartLineItemDTO addCartLineItemDTO) throws Exception;

	float calTotalPriceInCart(long cartId);
	
}
