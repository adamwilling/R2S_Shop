package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenvosongtoan.r2sshop.dto.AddCartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.dto.CartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.entity.CartLineItem;

@Mapper(componentModel = "spring")
public interface CartLineItemMapper {

    @Mapping(source = "cartDTO", target = "cart")
    CartLineItem toEntity(CartLineItemDTO cartLineItemDTO);
    @Mapping(source = "variantProduct", target = "variantProductDTO")
    @Mapping(source = "cart", target = "cartDTO")
    CartLineItemDTO toDTO(CartLineItem CartLineItem);

    @Mapping(source = "variantProductDTO", target = "variantProduct")
    CartLineItem toEntity(AddCartLineItemDTO addCartLineItemDTO);
    @Mapping(source = "variantProduct", target = "variantProductDTO")
    AddCartLineItemDTO toAddDTO(CartLineItem CartLineItem);
}
