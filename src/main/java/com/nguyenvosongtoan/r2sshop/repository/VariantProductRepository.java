package com.nguyenvosongtoan.r2sshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenvosongtoan.r2sshop.dto.VariantProductDTO;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;

import java.util.List;

@Repository
public interface VariantProductRepository extends JpaRepository<VariantProduct,Long> {
    List<VariantProduct> findByProductId(long id);
}
