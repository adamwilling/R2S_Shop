package com.nguyenvosongtoan.r2sshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.dto.ProductCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.VariantProCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.VariantProductDTO;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;
import com.nguyenvosongtoan.r2sshop.mapper.ProductMapper;
import com.nguyenvosongtoan.r2sshop.mapper.VariantProductMapper;
import com.nguyenvosongtoan.r2sshop.repository.VariantProductRepository;
import com.nguyenvosongtoan.r2sshop.service.ProductService;
import com.nguyenvosongtoan.r2sshop.service.VariantProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariantProductServiceImpl implements VariantProductService {
    private final VariantProductRepository variantProductRepository;
    private final VariantProductMapper variantProductMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    // Constructor DI để Inject các dependency
    public VariantProductServiceImpl(
            VariantProductRepository variantProductRepository,
            VariantProductMapper variantProductMapper,
            ProductService productService,
            ProductMapper productMapper) {
        this.variantProductRepository = variantProductRepository;
        this.variantProductMapper = variantProductMapper;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    /**
     * Tìm kiếm danh sách các sản phẩm biến thể theo ID sản phẩm.
     *
     * @param id ID sản phẩm
     * @return Danh sách các đối tượng VariantProductDTO
     */
    @Transactional(readOnly = true)
    @Override
    public List<VariantProductDTO> findByProductId(long id) {
        return variantProductRepository.findByProductId(id).stream()
                .map(variantProduct -> variantProductMapper.toDTO(variantProduct))
                .toList();
    }

    /**
     * Tạo mới danh sách các sản phẩm biến thể.
     *
     * @param productId          ID sản phẩm
     * @param variantProCreateDTO Danh sách các đối tượng VariantProCreateDTO chứa thông tin sản phẩm biến thể cần tạo mới
     * @return Danh sách các đối tượng VariantProductDTO đã được tạo mới
     * @throws Exception Nếu xảy ra lỗi trong quá trình tạo mới sản phẩm biến thể
     */
    @Transactional
    @Override
    public List<VariantProductDTO> create(long productId, List<VariantProCreateDTO> variantProCreateDTO) throws Exception {
        ProductCreateDTO product = productService.findById(productId);
        List<VariantProduct> variantProducts = variantProCreateDTO.stream()
                .map(variantProCreateDTO1 -> variantProductMapper.toEntity(variantProCreateDTO1))
                .toList();
        variantProducts.forEach(variantProduct -> variantProduct.setProduct(productMapper.toEntity(product)));
        return variantProductRepository.saveAll(variantProducts).stream()
                .map(variantProduct -> variantProductMapper.toDTO(variantProduct))
                .toList();
    }

    /**
     * Cập nhật danh sách các sản phẩm biến thể.
     *
     * @param variantProductDTOS Danh sách các đối tượng VariantProductDTO chứa thông tin sản phẩm biến thể cần cập nhật
     * @return Danh sách các đối tượng VariantProductDTO đã được cập nhật
     * @throws Exception Nếu xảy ra lỗi trong quá trình cập nhật sản phẩm biến thể
     */
    @Transactional
    @Override
    public List<VariantProductDTO> update(List<VariantProductDTO> variantProductDTOS) throws Exception {
        List<VariantProductDTO> newVariantProductDTOS = new ArrayList<>();
        for (VariantProductDTO variantProductDTO : variantProductDTOS) {
            VariantProduct existedvariantProduct = findById(variantProductDTO.getId());
            existedvariantProduct.setColor(variantProductDTO.getColor());
            existedvariantProduct.setSize(variantProductDTO.getSize());
            newVariantProductDTOS.add(variantProductMapper.toDTO(variantProductRepository.save(existedvariantProduct)));
        }
        return newVariantProductDTOS;
    }

    /**
     * Lấy thông tin sản phẩm biến thể theo ID.
     *
     * @param id ID sản phẩm biến thể
     * @return Đối tượng VariantProduct
     * @throws Exception Nếu không tìm thấy sản phẩm biến thể
     */
    @Override
    public VariantProduct findById(long id) throws Exception {
        return variantProductRepository.findById(id)
                .orElseThrow(() -> new Exception("Kiểm tra lại ID sản phẩm biến thể!"));
    }
}
