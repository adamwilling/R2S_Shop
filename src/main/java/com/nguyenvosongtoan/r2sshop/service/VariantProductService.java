package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.VariantProCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.VariantProductDTO;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;
import com.nguyenvosongtoan.r2sshop.exception.ProductNotFoundException;
import com.nguyenvosongtoan.r2sshop.exception.VariantProductNotFoundException;

public interface VariantProductService {
	
    List<VariantProductDTO> findByProductId(long id);

    List<VariantProductDTO> create(long productId, List<VariantProCreateDTO> variantProCreateDTOs) throws ProductNotFoundException;

    List<VariantProductDTO> update(List<VariantProductDTO> variantProductDTOS) throws VariantProductNotFoundException;

    VariantProduct findById(long id) throws VariantProductNotFoundException;
    
}
