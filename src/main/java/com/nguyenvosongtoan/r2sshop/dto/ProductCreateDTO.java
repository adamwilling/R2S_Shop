package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductCreateDTO{
    private String name;
    private float price;
    private String description;
    private CategoryDTO categoryDTO;
    private List<VariantProductDTO> variantProductsDTOs;
}
