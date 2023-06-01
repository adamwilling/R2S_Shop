package com.nguyenvosongtoan.r2sshop.service;


import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.CategoryDTO;
import com.nguyenvosongtoan.r2sshop.exception.CategoryNotFoundException;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO create(CategoryDTO categoryDTO);
    
    CategoryDTO update(CategoryDTO categoryDTO) throws CategoryNotFoundException;

    CategoryDTO findById(long id) throws CategoryNotFoundException;

}
