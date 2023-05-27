package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;

import com.nguyenvosongtoan.r2sshop.dto.VariantProCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.VariantProductDTO;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;

@Mapper(componentModel = "spring")
public interface VariantProductMapper {
    VariantProduct toEntity(VariantProductDTO variantProductDTO);

    VariantProductDTO toDTO(VariantProduct variantProduct);

    VariantProduct toEntity(VariantProCreateDTO variantProCreateDTO);

}
