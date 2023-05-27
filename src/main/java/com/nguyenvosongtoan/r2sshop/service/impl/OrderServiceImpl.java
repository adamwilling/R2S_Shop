package com.nguyenvosongtoan.r2sshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.dto.*;
import com.nguyenvosongtoan.r2sshop.entity.Address;
import com.nguyenvosongtoan.r2sshop.entity.Order;
import com.nguyenvosongtoan.r2sshop.mapper.OrderMapper;
import com.nguyenvosongtoan.r2sshop.repository.OrderRepository;
import com.nguyenvosongtoan.r2sshop.service.*;

import java.util.Calendar;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderMapper orderMapper;
    private final CartLineItemService cartLineItemService;
    private final UserService userService;
    private final AddressService addressService;

    // Constructor DI để Inject các dependency
    public OrderServiceImpl(
            OrderRepository orderRepository,
            CartService cartService,
            OrderMapper orderMapper,
            CartLineItemService cartLineItemService,
            UserService userService,
            AddressService addressService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.orderMapper = orderMapper;
        this.cartLineItemService = cartLineItemService;
        this.userService = userService;
        this.addressService = addressService;
    }

    /**
     * Tìm kiếm đơn hàng theo ID.
     *
     * @param id ID của đơn hàng cần tìm kiếm
     * @return Đối tượng OrderDTO tương ứng với ID
     * @throws Exception Nếu không tìm thấy đơn hàng
     */
    @Transactional(readOnly = true)
    @Override
    public OrderDTO findById(long id) throws Exception {
        return orderMapper.toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Đơn hàng này không tồn tại!")));
    }

    /**
     * Đặt hàng từ giỏ hàng.
     *
     * @param orderACartDTO Đối tượng OrderACartDTO chứa thông tin đặt hàng
     * @return Đối tượng OrderDTO sau khi đặt hàng
     * @throws Exception Nếu xảy ra lỗi trong quá trình đặt hàng
     */
    @Transactional
    @Override
    public OrderDTO order(OrderACartDTO orderACartDTO) throws Exception {
        UserDTO userDTO = userService.getCurrentUser();
        Address address = addressService.findById(orderACartDTO.getAddressId());
        CartDTO cartDTO = cartService.findById(orderACartDTO.getCartId());
        if (checkBeforeOrder(cartDTO, userDTO, address)) {
            float totalPrice = cartLineItemService.calTotalPriceInCart(orderACartDTO.getCartId());
            // Thêm 7 ngày từ ngày hiện tại
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 7);
            dt = c.getTime();
            // Thêm các giá trị trước khi lưu đơn hàng mới
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setCartDTO(cartDTO);
            orderDTO.setUserDTO(userDTO);
            orderDTO.setTotalPrice(totalPrice);
            orderDTO.setArrived(false);
            orderDTO.setEstimatedArrivalTime(dt);
            Order order = orderMapper.toEntity(orderDTO);
            order.setAddress(address);
            // Lưu đơn hàng mới
            orderDTO = orderMapper.toDTO(orderRepository.save(order));
            // Cập nhật trạng thái giỏ hàng
            cartDTO = cartService.updateCartStatus(orderACartDTO.getCartId(), "delivering");
            orderDTO.setCartDTO(cartDTO);
            cartLineItemService.delAlLCartLineItemByCartId(orderACartDTO.getCartId());
            return orderDTO;
        }
        return null;
    }

    /**
     * Kiểm tra các điều kiện trước khi đặt hàng.
     *
     * @param cartDTO  Đối tượng CartDTO
     * @param userDTO  Đối tượng UserDTO
     * @param address  Đối tượng Address
     * @return True nếu các điều kiện đúng, False nếu có lỗi
     * @throws Exception Nếu xảy ra lỗi trong quá trình kiểm tra
     */
    private boolean checkBeforeOrder(CartDTO cartDTO, UserDTO userDTO, Address address) throws Exception {
        if (cartDTO.getUser().getId() != userDTO.getId())
            throw new Exception("Người dùng hiện tại không có giỏ hàng này!");
        if (!cartDTO.getStatus().equals("new"))
            throw new Exception("Giỏ hàng này đã được đặt hàng!");
        if (userDTO.getId() != address.getUser().getId())
            throw new Exception("Người dùng hiện tại không có địa chỉ này!");
        return true;
    }
}
