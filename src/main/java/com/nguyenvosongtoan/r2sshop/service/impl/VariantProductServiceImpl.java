package com.nguyenvosongtoan.r2sshop.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;
import com.nguyenvosongtoan.r2sshop.exception.NotFoundException;
import com.nguyenvosongtoan.r2sshop.repository.VariantProductRepository;
import com.nguyenvosongtoan.r2sshop.service.VariantProductService;
import com.nguyenvosongtoan.r2sshop.util.PaginationSortingUtils;

@Service
public class VariantProductServiceImpl implements VariantProductService {
	private final VariantProductRepository variantProductRepository;

	public VariantProductServiceImpl(VariantProductRepository variantProductRepository) {
		this.variantProductRepository = variantProductRepository;
	}

    @Transactional(readOnly = true)
    @Override
    public VariantProduct getById(Long key) {
        Optional<VariantProduct> variantProduct = variantProductRepository.findById(key);
        if (variantProduct.isPresent()) {
            return variantProduct.get();
        } else {
            throw new NotFoundException("Not found variantProduct");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<VariantProduct> getAll(int page, int limit, String sort, String order) {
        Pageable pageable = PaginationSortingUtils.getPageable(page, limit, sort, order);
        List<VariantProduct> categories = variantProductRepository.findAll(pageable).getContent();
        if (!categories.isEmpty()) {
            return categories;
        } else {
            return Collections.emptyList();
        }
    }

	@Transactional(readOnly = true)
	@Override
	public List<VariantProduct> getVariantProductsByProductId(Long productId) {
		return variantProductRepository.findVariantProductsByProductId(productId);
	}

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public VariantProduct create(VariantProduct variantProduct) {
    	variantProduct.setId(null);
        variantProductRepository.save(variantProduct);
        return variantProduct;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public VariantProduct update(VariantProduct variantProduct) {
        Optional<VariantProduct> existedVariantProduct = variantProductRepository.findById(variantProduct.getId());
        if (existedVariantProduct.isPresent()) {
            variantProductRepository.save(variantProduct);
            return variantProduct;
        } else {
            throw new NotFoundException("Not found variantProduct");
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void delete(Long id) {
        Optional<VariantProduct> existedVariantProduct = variantProductRepository.findById(id);
        if (existedVariantProduct.isPresent()) {
            variantProductRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found variantProduct");
        }
    }
}