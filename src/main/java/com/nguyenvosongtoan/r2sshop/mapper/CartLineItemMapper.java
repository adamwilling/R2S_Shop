package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenvosongtoan.r2sshop.dto.AddCartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.dto.CartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.entity.CartLineItem;

@Mapper(componentModel = "spring")
public interface CartLineItemMapper {

    @Mapping(source = "productDTO", target = "product")
    @Mapping(source = "cartDTO", target = "cart")
    CartLineItem toEntity(CartLineItemDTO cartLineItemDTO);
    @Mapping(source = "product", target = "productDTO")
    @Mapping(source = "cart", target = "cartDTO")
    CartLineItemDTO toDTO(CartLineItem CartLineItem);

    @Mapping(source = "productDTO", target = "product")
    CartLineItem toEntity(AddCartLineItemDTO addCartLineItemDTO);
    @Mapping(source = "product", target = "productDTO")
    AddCartLineItemDTO toAddDTO(CartLineItem CartLineItem);
}
