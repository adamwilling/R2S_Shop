package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenvosongtoan.r2sshop.dto.ProductCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.ProductDTO;
import com.nguyenvosongtoan.r2sshop.dto.UpdateProductDTO;
import com.nguyenvosongtoan.r2sshop.entity.Product;

@Mapper(componentModel = "spring", uses = {VariantProductMapper.class, CategoryMapper.class, CartLineItemMapper.class})
public interface ProductMapper {
    @Mapping(source = "categoryDTO", target = "category")
    @Mapping(source = "variantProductsDTOs", target = "variantProducts")
    Product toEntity(ProductCreateDTO productCreateDTO);

    @Mapping(source = "category", target = "categoryDTO")
    @Mapping(source = "variantProducts", target = "variantProductsDTOs")
    ProductCreateDTO toCreateDTO(Product product);

    @Mapping(source = "categoryDTO", target = "category")
    @Mapping(source = "variantProductsDTOs", target = "variantProducts")
    Product toEntity(UpdateProductDTO updateProductDTO);

    @Mapping(source = "category", target = "categoryDTO")
    @Mapping(source = "variantProducts", target = "variantProductsDTOs")
    UpdateProductDTO toUpdateDTO(Product product);

    @Mapping(source = "categoryDTO", target = "category")
    @Mapping(source = "variantProductsDTOs", target = "variantProducts")
    Product toEntity(ProductDTO productDTO);

    @Mapping(source = "category", target = "categoryDTO")
    @Mapping(source = "variantProducts", target = "variantProductsDTOs")
    ProductDTO toDTO(Product product);


}
