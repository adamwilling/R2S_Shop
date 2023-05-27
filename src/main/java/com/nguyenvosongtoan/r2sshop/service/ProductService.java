package com.nguyenvosongtoan.r2sshop.service;

import com.nguyenvosongtoan.r2sshop.dto.PaginationDTO;
import com.nguyenvosongtoan.r2sshop.dto.ProductCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.UpdateProductDTO;
import com.nguyenvosongtoan.r2sshop.exception.CategoryNotFoundException;
import com.nguyenvosongtoan.r2sshop.exception.ProductNotFoundException;

public interface ProductService {
	
    PaginationDTO findAllProductByCategoryId(long categoryId, int no , int limit) throws ProductNotFoundException;

    ProductCreateDTO create(ProductCreateDTO productCreateDTO) throws ProductNotFoundException;

    ProductCreateDTO findById(long id) throws ProductNotFoundException;

    UpdateProductDTO update(UpdateProductDTO updateProductDTO) throws ProductNotFoundException;
    
}
