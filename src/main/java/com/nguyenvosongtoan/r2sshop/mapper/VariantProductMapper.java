package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenvosongtoan.r2sshop.dto.VariantProCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.VariantProductDTO;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;

@Mapper(componentModel = "spring")
public interface VariantProductMapper {
    @Mapping(source = "cartLineItemDTOs", target = "cartLineItems")
    VariantProduct toEntity(VariantProductDTO variantProductDTO);

    @Mapping(source = "cartLineItems", target = "cartLineItemDTOs")
    VariantProductDTO toDTO(VariantProduct variantProduct);

    VariantProduct toEntity(VariantProCreateDTO variantProCreateDTO);

}
