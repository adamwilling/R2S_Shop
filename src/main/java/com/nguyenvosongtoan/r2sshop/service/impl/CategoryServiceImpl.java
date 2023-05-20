package com.nguyenvosongtoan.r2sshop.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.entity.Category;
import com.nguyenvosongtoan.r2sshop.exception.NotFoundException;
import com.nguyenvosongtoan.r2sshop.repository.CategoryRepository;
import com.nguyenvosongtoan.r2sshop.service.CategoryService;
import com.nguyenvosongtoan.r2sshop.util.PaginationSortingUtils;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Category getById(Long key) {
        Optional<Category> category = categoryRepository.findById(key);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new NotFoundException("Not found category");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll(int page, int limit, String sort, String order) {
        Pageable pageable = PaginationSortingUtils.getPageable(page, limit, sort, order);
        List<Category> categories = categoryRepository.findAll(pageable).getContent();
        if (!categories.isEmpty()) {
            return categories;
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    @Override
    public Category create(Category category) {
    	category.setId(null);
        categoryRepository.save(category);
        return category;
    }

    @Transactional
    @Override
    public Category update(Category category) {
        Optional<Category> existedCategory = categoryRepository.findById(category.getId());
        if (existedCategory.isPresent()) {
            categoryRepository.save(category);
            return category;
        } else {
            throw new NotFoundException("Not found category");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Category> existedCategory = categoryRepository.findById(id);
        if (existedCategory.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found category");
        }
    }
}