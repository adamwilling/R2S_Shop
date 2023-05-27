package com.nguyenvosongtoan.r2sshop.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.dto.CartDTO;
import com.nguyenvosongtoan.r2sshop.dto.UserDTO;
import com.nguyenvosongtoan.r2sshop.entity.Cart;
import com.nguyenvosongtoan.r2sshop.exception.CartNotFoundException;
import com.nguyenvosongtoan.r2sshop.exception.UserNotFoundException;
import com.nguyenvosongtoan.r2sshop.mapper.CartMapper;
import com.nguyenvosongtoan.r2sshop.repository.CartRepository;
import com.nguyenvosongtoan.r2sshop.service.CartService;
import com.nguyenvosongtoan.r2sshop.service.UserService;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserService userService;

    // Constructor DI để Inject các dependency
    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper, @Lazy UserService userService) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.userService = userService;
    }

    /**
     * Cập nhật trạng thái của giỏ hàng.
     *
     * @param cartId   ID của giỏ hàng
     * @param status   Trạng thái mới
     * @return Đối tượng CartDTO sau khi được cập nhật trạng thái
     * @throws Exception Nếu không tìm thấy giỏ hàng
     */
    @Transactional
    @Override
    public CartDTO updateCartStatus(long cartId, String status) throws CartNotFoundException {
        CartDTO cartDTO = findById(cartId);
        cartDTO.setStatus(status);
        return cartMapper.toDTO(cartRepository.save(cartMapper.toEntity(cartDTO)));
    }

    /**
     * Lưu giỏ hàng vào cơ sở dữ liệu.
     *
     * @param cart Giỏ hàng cần lưu
     * @return Giỏ hàng đã được lưu
     */
    @Transactional
    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    /**
     * Lấy danh sách các giỏ hàng của người dùng đang đăng nhập.
     *
     * @return Danh sách các CartDTO của người dùng đang đăng nhập
     * @throws Exception Nếu có lỗi xảy ra
     */
    @Transactional(readOnly = true)
    @Override
    public List<CartDTO> getAllCartsFromLoginUser() throws UserNotFoundException {
        // Lấy thông tin người dùng đang đăng nhập từ UserService
        UserDTO userDTO = userService.getCurrentUser();

        // Lấy danh sách giỏ hàng của người dùng từ CartRepository
        List<Cart> carts = cartRepository.findAllByUserId(userDTO.getId());

        // Chuyển đổi danh sách Cart thành danh sách CartDTO bằng cách sử dụng CartMapper
        return carts.stream().map(cart -> cartMapper.toDTO(cart)).toList();
    }

    /**
     * Tìm kiếm danh sách các giỏ hàng theo ID người dùng và trạng thái.
     *
     * @param status Trạng thái của giỏ hàng
     * @return Danh sách các CartDTO tương ứng với ID người dùng và trạng thái
     * @throws Exception Nếu có lỗi xảy ra
     */
    @Transactional(readOnly = true)
    @Override
    public List<CartDTO> findByUserIdAndStatus(String status) throws UserNotFoundException {
        // Lấy thông tin người dùng đang đăng nhập từ UserService
        UserDTO userDTO = userService.getCurrentUser();

        // Tìm kiếm danh sách giỏ hàng theo ID người dùng và trạng thái từ CartRepository
        List<Cart> carts = cartRepository.findCartByUserIdAndStatus(userDTO.getId(), status);

        // Chuyển đổi danh sách Cart thành danh sách CartDTO bằng cách sử dụng CartMapper
        return carts.stream().map(cart -> cartMapper.toDTO(cart)).toList();
    }

    /**
     * Tìm kiếm giỏ hàng theo ID.
     *
     * @param cartId ID của giỏ hàng cần tìm kiếm
     * @return Đối tượng CartDTO tương ứng với ID
     * @throws Exception Nếu không tìm thấy giỏ hàng
     */
    @Transactional(readOnly = true)
    @Override
    public CartDTO findById(long cartId) throws CartNotFoundException {
        return cartMapper.toDTO(cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Giỏ hàng này không tồn tại!")));
    }
}
