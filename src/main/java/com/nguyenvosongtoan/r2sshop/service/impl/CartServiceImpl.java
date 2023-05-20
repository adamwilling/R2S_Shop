package com.nguyenvosongtoan.r2sshop.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.nguyenvosongtoan.r2sshop.entity.Address;
import com.nguyenvosongtoan.r2sshop.entity.Cart;
import com.nguyenvosongtoan.r2sshop.entity.CartLineItem;
import com.nguyenvosongtoan.r2sshop.entity.Order;
import com.nguyenvosongtoan.r2sshop.entity.Product;
import com.nguyenvosongtoan.r2sshop.entity.User;
import com.nguyenvosongtoan.r2sshop.entity.VariantProduct;
import com.nguyenvosongtoan.r2sshop.exception.NotFoundException;
import com.nguyenvosongtoan.r2sshop.repository.AddressRepository;
import com.nguyenvosongtoan.r2sshop.repository.CartRepository;
import com.nguyenvosongtoan.r2sshop.repository.OrderRepository;
import com.nguyenvosongtoan.r2sshop.service.AddressService;
import com.nguyenvosongtoan.r2sshop.service.CartService;
import com.nguyenvosongtoan.r2sshop.service.ProductService;
import com.nguyenvosongtoan.r2sshop.service.VariantProductService;

@Service
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;
	private final AddressService addressService;
	private final OrderRepository orderRepository;
	private final VariantProductService variantProductService;

	public CartServiceImpl(CartRepository cartRepository, AddressService addressService, OrderRepository orderRepository,
			VariantProductService variantProductService) {
		this.cartRepository = cartRepository;
		this.addressService = addressService;
		this.orderRepository = orderRepository;
		this.variantProductService = variantProductService;
	}

	@Transactional
	@Override
	public Cart create(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setCreatedAt(new Date());
		return cartRepository.save(cart);
	}

	@Transactional
	@Override
	public Cart getById(Long cartId) {
		Optional<Cart> cartOptional = cartRepository.findById(cartId);
		return cartOptional.orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public Cart getByUserId(Long userId) {
		Optional<Cart> cart = cartRepository.findByUserId(userId);
		if (cart.isPresent()) {
			return cart.get();
		} else {
			throw new NotFoundException("Not found cart");
		}
	}

	@Transactional
	@Override
	public ResponseEntity<Cart> addProductToCart(String json) {
		JsonNode jsonNode;
		JsonMapper jsonMapper = new JsonMapper();
		Long cartId;
		Long variantProductId;
		int quantity;
		try {
			jsonNode = jsonMapper.readTree(json);
			cartId = jsonNode.get("cartId") != null ? jsonNode.get("cartId").asLong() : -1;
			variantProductId = jsonNode.get("variantProductId") != null ? jsonNode.get("variantProductId").asLong()
					: -1;
			quantity = jsonNode.get("quantity") != null ? jsonNode.get("quantity").asInt() : -1;
			Cart cart = getById(cartId);
			VariantProduct product = variantProductService.getById(variantProductId);
			if (product != null) {
				List<CartLineItem> cartLineItems = cart.getCartLineItems();
				Optional<CartLineItem> cartLineItemOptional = cartLineItems.stream()
						.filter(cli -> cli.getVariantProduct().getId().equals(variantProductId)).findFirst();
				if (cartLineItemOptional.isPresent()) {
					CartLineItem cartLineItem = cartLineItemOptional.get();
					int newQuantity = cartLineItem.getQuantity() + quantity;
					cartLineItem.setQuantity(newQuantity);
				} else {
					CartLineItem cartLineItem = new CartLineItem(product, quantity);
					cartLineItems.add(cartLineItem);
				}
				Cart savedCart = cartRepository.save(cart);
				return new ResponseEntity<Cart>(savedCart, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	@Transactional
	@Override
	public BigDecimal calculateTotalPrice(Cart cart) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (CartLineItem cartLineItem : cart.getCartLineItems()) {
			BigDecimal lineItemPrice = cartLineItem.getVariantProduct().getPrice()
					.multiply(BigDecimal.valueOf(cartLineItem.getQuantity()));
			totalPrice = totalPrice.add(lineItemPrice);
		}
		return totalPrice;
	}

	@Transactional
	@Override
	public Order createOrderFromCart(Long cartId, Long addressId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));

		User user = cart.getUser();
		Address address = addressService.getById(addressId);
		Date createdAt = new Date();

		Order order = new Order(user, calculateTotalPrice(cart), createdAt, address);
		orderRepository.save(order);

		List<CartLineItem> cartLineItems = cart.getCartLineItems();
		cartLineItems.forEach(cartLineItem -> {
			cartLineItem.setCart(null);
			cartLineItem.setIsDeleted(true);
		});

		cart.setCartLineItems(cartLineItems);
		orderRepository.save(order);

		return order;
	}

}