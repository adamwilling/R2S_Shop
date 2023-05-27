package com.nguyenvosongtoan.r2sshop.service;

import com.nguyenvosongtoan.r2sshop.dto.PaginationDTO;
import com.nguyenvosongtoan.r2sshop.dto.ProductCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.UpdateProductDTO;

public interface ProductService {
	
    PaginationDTO findAllProductByCategoryId(long categoryId, int no , int limit) throws Exception;

    ProductCreateDTO create(ProductCreateDTO productCreateDTO) throws Exception;

    ProductCreateDTO findById(long id) throws Exception;

    UpdateProductDTO update(UpdateProductDTO updateProductDTO) throws Exception;
    
}
