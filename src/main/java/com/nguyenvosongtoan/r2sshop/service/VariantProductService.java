package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;

public interface VariantProductService extends BaseService<VariantProduct, Long> {
    List<VariantProduct> getVariantProductsByProductId(Long productId);
}