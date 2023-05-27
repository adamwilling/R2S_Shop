package com.nguyenvosongtoan.r2sshop.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.dto.CategoryDTO;
import com.nguyenvosongtoan.r2sshop.dto.PaginationDTO;
import com.nguyenvosongtoan.r2sshop.dto.ProductCreateDTO;
import com.nguyenvosongtoan.r2sshop.dto.UpdateProductDTO;
import com.nguyenvosongtoan.r2sshop.entity.Product;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;
import com.nguyenvosongtoan.r2sshop.exception.CategoryNotFoundException;
import com.nguyenvosongtoan.r2sshop.exception.ProductNotFoundException;
import com.nguyenvosongtoan.r2sshop.mapper.ProductMapper;
import com.nguyenvosongtoan.r2sshop.mapper.VariantProductMapper;
import com.nguyenvosongtoan.r2sshop.repository.ProductRepository;
import com.nguyenvosongtoan.r2sshop.repository.VariantProductRepository;
import com.nguyenvosongtoan.r2sshop.service.CategoryService;
import com.nguyenvosongtoan.r2sshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final VariantProductRepository variantProductRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    // Constructor DI để Inject các dependency
    public ProductServiceImpl(
            ProductRepository productRepository,
            VariantProductRepository variantProductRepository,
            ProductMapper productMapper,
            VariantProductMapper variantProductMapper,
            CategoryService categoryService) {
        this.productRepository = productRepository;
        this.variantProductRepository = variantProductRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    /**
     * Tìm kiếm các sản phẩm theo ID danh mục.
     *
     * @param categoryId ID của danh mục
     * @param no         Số trang
     * @param limit      Số lượng sản phẩm trên mỗi trang
     * @return Đối tượng PaginationDTO chứa danh sách sản phẩm và thông tin phân trang
     * @throws Exception Nếu không tìm thấy sản phẩm
     */
    @Transactional(readOnly = true)
    @Override
    public PaginationDTO findAllProductByCategoryId(long categoryId, int no, int limit) throws ProductNotFoundException {
        Page<ProductCreateDTO> productCreateDTOPages = productRepository.findAllByCategoryIdOrderByPriceDesc(categoryId, PageRequest.of(no, limit))
                .map(product -> productMapper.toCreateDTO(product));
        if (productCreateDTOPages.getTotalElements() == 0)
            throw new ProductNotFoundException("Không tìm thấy sản phẩm!");
        return new PaginationDTO(productCreateDTOPages.getContent(), productCreateDTOPages.isFirst(), productCreateDTOPages.isLast(), productCreateDTOPages.getTotalPages(),
                productCreateDTOPages.getTotalElements(), productCreateDTOPages.getSize(), productCreateDTOPages.getNumber());
    }

    /**
     * Tạo mới một sản phẩm.
     *
     * @param productCreateDTO Đối tượng ProductCreateDTO chứa thông tin sản phẩm cần tạo
     * @return Đối tượng ProductCreateDTO đã được tạo mới
     * @throws Exception Nếu xảy ra lỗi trong quá trình tạo mới sản phẩm
     */
    @Transactional
    @Override
    public ProductCreateDTO create(ProductCreateDTO productCreateDTO) throws CategoryNotFoundException {
        CategoryDTO categoryDTO = categoryService.findById(productCreateDTO.getCategoryDTO().getId());
        productCreateDTO.setCategoryDTO(categoryDTO);
        Product product = productMapper.toEntity(productCreateDTO);
        product = productRepository.save(product);
        List<VariantProduct> list = new ArrayList<>();
        for (VariantProduct variantProduct : product.getVariantProducts()) {
            variantProduct.setProduct(product);
            list.add(variantProductRepository.save(variantProduct));
        }
        product.setVariantProducts(list);
        return productMapper.toCreateDTO(product);
    }

    /**
     * Tìm kiếm một sản phẩm theo ID.
     *
     * @param id ID của sản phẩm
     * @return Đối tượng ProductCreateDTO tương ứng với ID
     * @throws Exception Nếu sản phẩm không tồn tại
     */
    @Transactional(readOnly = true)
    @Override
    public ProductCreateDTO findById(long id) throws ProductNotFoundException {
        return productMapper.toCreateDTO(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Sản phẩm không tồn tại!")));
    }

    /**
     * Cập nhật thông tin một sản phẩm.
     *
     * @param updateProductDTO Đối tượng UpdateProductDTO chứa thông tin sản phẩm cần cập nhật
     * @return Đối tượng UpdateProductDTO đã được cập nhật
     * @throws Exception Nếu xảy ra lỗi trong quá trình cập nhật sản phẩm
     */
    @Transactional
    @Override
    public UpdateProductDTO update(UpdateProductDTO updateProductDTO) throws ProductNotFoundException {
        Product existedProduct = productMapper.toEntity(findById(updateProductDTO.getId()));
        CategoryDTO categoryDTO = categoryService.findById(updateProductDTO.getCategoryDTO().getId());
        updateProductDTO.setCategoryDTO(categoryDTO);
        Product product = productMapper.toEntity(updateProductDTO);
        existedProduct.getVariantProducts().forEach(variantProduct -> variantProduct.setProduct(product));
        product.setVariantProducts(existedProduct.getVariantProducts());
        return productMapper.toUpdateDTO(productRepository.save(product));
    }
}
