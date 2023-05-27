package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.VariantProCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.VariantProductDTO;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;

public interface VariantProductService {
	
    List<VariantProductDTO> findByProductId(long id);

    List<VariantProductDTO> create(long productId, List<VariantProCreateDTO> variantProCreateDTOs) throws Exception;

    List<VariantProductDTO> update(List<VariantProductDTO> variantProductDTOS) throws Exception;

    VariantProduct findById(long id) throws Exception;
    
}
