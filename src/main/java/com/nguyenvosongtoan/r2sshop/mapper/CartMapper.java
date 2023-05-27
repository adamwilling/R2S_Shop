package com.nguyenvosongtoan.r2sshop.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenvosongtoan.r2sshop.dto.CartDTO;
import com.nguyenvosongtoan.r2sshop.entity.Cart;

@Mapper(componentModel = "spring",uses = {CartLineItemMapper.class})
public interface CartMapper {
    @Mapping(source = "cartLineItemDTOs", target = "cartLineItems")
    Cart toEntity(CartDTO cartDTO);
    @Mapping(source = "cartLineItems", target = "cartLineItemDTOs")
    CartDTO toDTO(Cart cart);
}
