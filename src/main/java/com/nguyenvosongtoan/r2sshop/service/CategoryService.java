package com.nguyenvosongtoan.r2sshop.service;


import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.CategoryDTO;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO create(CategoryDTO categoryDTO);

    CategoryDTO findById(long id) throws Exception;

}
