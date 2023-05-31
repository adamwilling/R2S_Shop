package com.nguyenvosongtoan.r2sshop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.dto.AddCartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.dto.CartLineItemDTO;
import com.nguyenvosongtoan.r2sshop.dto.UserDTO;
import com.nguyenvosongtoan.r2sshop.entity.Cart;
import com.nguyenvosongtoan.r2sshop.entity.CartLineItem;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;
import com.nguyenvosongtoan.r2sshop.exception.CartLineItemNotFoundException;
import com.nguyenvosongtoan.r2sshop.mapper.CartLineItemMapper;
import com.nguyenvosongtoan.r2sshop.mapper.CartMapper;
import com.nguyenvosongtoan.r2sshop.mapper.UserMapper;
import com.nguyenvosongtoan.r2sshop.repository.CartLineItemRepository;
import com.nguyenvosongtoan.r2sshop.repository.CartRepository;
import com.nguyenvosongtoan.r2sshop.repository.VariantProductRepository;
import com.nguyenvosongtoan.r2sshop.service.CartLineItemService;
import com.nguyenvosongtoan.r2sshop.service.CartService;
import com.nguyenvosongtoan.r2sshop.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class CartLineItemServiceImpl implements CartLineItemService {
	private final CartLineItemRepository cartLineItemRepository;
	private final CartLineItemMapper cartLineItemMapper;
	private final UserService userService;
	private final CartService cartService;
	private final VariantProductRepository variantProductRepository;
	private final CartMapper cartMapper;
	private final UserMapper userMapper;
	private final CartRepository cartRepository;

	// Constructor DI để Inject các dependency
	public CartLineItemServiceImpl(CartLineItemRepository cartLineItemRepository, CartLineItemMapper cartLineItemMapper,
			UserService userService, CartService cartService, VariantProductRepository variantProductRepository,
			CartMapper cartMapper, UserMapper userMapper, CartRepository cartRepository) {
		this.cartLineItemRepository = cartLineItemRepository;
		this.cartLineItemMapper = cartLineItemMapper;
		this.userService = userService;
		this.cartService = cartService;
		this.variantProductRepository = variantProductRepository;
		this.cartMapper = cartMapper;
		this.userMapper = userMapper;
		this.cartRepository = cartRepository;
	}

	/**
	 * Lấy danh sách CartLineItemDTO theo ID của Cart.
	 *
	 * @param id ID của Cart
	 * @return Danh sách CartLineItemDTO
	 */
	@Transactional(readOnly = true)
	@Override
	public List<CartLineItemDTO> getAllByCartId(long id) {
		return cartLineItemRepository.findAllByCartId(id).stream()
				.map(cartLineItem -> cartLineItemMapper.toDTO(cartLineItem)).toList();
	}

	/**
	 * Thêm sản phẩm vào giỏ hàng.
	 *
	 * @param addCartLineItemDTO Thông tin sản phẩm cần thêm vào giỏ hàng
	 * @return CartLineItemDTO đã được thêm vào giỏ hàng
	 * @throws Exception Ném ngoại lệ nếu có lỗi xảy ra trong quá trình thêm vào giỏ
	 *                   hàng
	 */
	@Transactional
	@Override
	public CartLineItemDTO addProductIntoCart(AddCartLineItemDTO addCartLineItemDTO) throws Exception {
		UserDTO user = userService.getCurrentUser();
		VariantProduct variantProduct = variantProductRepository
				.findById(addCartLineItemDTO.getVariantProductDTO().getId())
				.orElseThrow(() -> new Exception("Sản phẩm không tồn tại!"));
		CartLineItem cartLineItem = cartLineItemMapper.toEntity(addCartLineItemDTO);
		cartLineItem.setVariantProduct(variantProduct);
		List<Cart> carts = cartService.findByUserIdAndStatus("new").stream()
				.map(cartDTO -> cartMapper.toEntity(cartDTO)).toList();
		if (carts.isEmpty()) {
			Cart cart = new Cart(0, "new", userMapper.toEntity(user), null, null);
			cart = cartRepository.save(cart);
			cartLineItem.setCart(cart);
		} else {
			Cart cart = carts.get(0);
			cartLineItem.setCart(cart);
			Optional<CartLineItem> existedCartLineItem = cartLineItemRepository.findByCartIdAndVariantProductId(cart.getId(),
					variantProduct.getId());
			if (existedCartLineItem.isPresent()) {
				cartLineItem.setDeleted(false);
				cartLineItem.setId(existedCartLineItem.get().getId());
				cartLineItem.setAmount(existedCartLineItem.get().getAmount() + cartLineItem.getAmount());
			}
		}
		return cartLineItemMapper.toDTO(cartLineItemRepository.save(cartLineItem));
	}

	/**
	 * Cập nhật thông tin sản phẩm trong giỏ hàng.
	 *
	 * @param cartLineItemDTO Thông tin sản phẩm cần cập nhật
	 * @return CartLineItemDTO đã được cập nhật
	 * @throws Exception Ném ngoại lệ nếu có lỗi xảy ra trong quá trình cập nhật
	 */
	@Transactional
	@Override
	public CartLineItemDTO updateProductInCart(CartLineItemDTO cartLineItemDTO) throws CartLineItemNotFoundException {
		CartLineItem cartLineItem = cartLineItemRepository.findById(cartLineItemDTO.getId()).orElseThrow(
				() -> new CartLineItemNotFoundException("Vui lòng kiểm tra lại thông tin sản phẩm trong giỏ hàng!"));
		cartLineItem.setAmount(cartLineItemDTO.getAmount());
		cartLineItem.setPrice(cartLineItemDTO.getPrice());
		return cartLineItemMapper.toDTO(cartLineItemRepository.save(cartLineItem));
	}

	/**
	 * Tính tổng giá tiền trong giỏ hàng.
	 *
	 * @param cartId ID của giỏ hàng
	 * @return Tổng giá tiền trong giỏ hàng
	 */
	@Transactional
	@Override
	public float calTotalPriceInCart(long cartId) {
		List<CartLineItemDTO> cartLineItemDTOS = getAllByCartId(cartId);
		float totalPrice = 0;
		for (CartLineItemDTO cartLineItemDTO : cartLineItemDTOS) {
			totalPrice += cartLineItemDTO.getPrice() * cartLineItemDTO.getAmount();
		}
		return totalPrice;
	}

	/**
	 * Xóa sản phẩm khỏi giỏ hàng.
	 *
	 * @param id ID của sản phẩm cần xóa
	 * @return CartLineItemDTO đã được xóa
	 * @throws Exception Ném ngoại lệ nếu có lỗi xảy ra trong quá trình xóa
	 */
	@Transactional
	@Override
	public CartLineItemDTO delProductIntoCart(long id) throws CartLineItemNotFoundException {
		CartLineItem cartLineItem = cartLineItemRepository.findById(id).orElseThrow(
				() -> new CartLineItemNotFoundException("Vui lòng kiểm tra lại thông tin sản phẩm trong giỏ hàng!"));
		cartLineItem.setDeleted(true);
		return cartLineItemMapper.toDTO(cartLineItemRepository.save(cartLineItem));
	}

	/**
	 * Xóa tất cả sản phẩm trong giỏ hàng theo ID của giỏ hàng.
	 *
	 * @param cartId ID của giỏ hàng
	 */
	@Transactional
	@Override
	public void delAlLCartLineItemByCartId(long cartId) {
		List<CartLineItemDTO> cartLineItemDTOS = getAllByCartId(cartId);
		for (CartLineItemDTO cartLineItemDTO : cartLineItemDTOS) {
			CartLineItem cartLineItem = cartLineItemMapper.toEntity(cartLineItemDTO);
			cartLineItem.setDeleted(true);
			cartLineItemRepository.save(cartLineItem);
		}
	}
}
