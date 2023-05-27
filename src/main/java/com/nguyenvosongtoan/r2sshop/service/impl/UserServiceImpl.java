package com.nguyenvosongtoan.r2sshop.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.dto.CreateUserDTO;
import com.nguyenvosongtoan.r2sshop.dto.UserDTO;
import com.nguyenvosongtoan.r2sshop.entity.Address;
import com.nguyenvosongtoan.r2sshop.entity.Cart;
import com.nguyenvosongtoan.r2sshop.entity.Role;
import com.nguyenvosongtoan.r2sshop.entity.User;
import com.nguyenvosongtoan.r2sshop.mapper.AddressMapper;
import com.nguyenvosongtoan.r2sshop.mapper.RoleMapper;
import com.nguyenvosongtoan.r2sshop.mapper.UserMapper;
import com.nguyenvosongtoan.r2sshop.repository.AddressRepository;
import com.nguyenvosongtoan.r2sshop.repository.RoleRepository;
import com.nguyenvosongtoan.r2sshop.repository.UserRepository;
import com.nguyenvosongtoan.r2sshop.service.CartService;
import com.nguyenvosongtoan.r2sshop.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CartService cartService;

    // Constructor DI để Inject các dependency
    public UserServiceImpl(
            AddressRepository addressRepository,
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserMapper userMapper,
            RoleMapper roleMapper,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            AddressMapper addressMapper,
            @Lazy CartService cartService) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cartService = cartService;
    }

    /**
     * Lấy thông tin người dùng hiện tại.
     *
     * @return Đối tượng UserDTO chứa thông tin người dùng hiện tại
     * @throws Exception Nếu không tìm thấy người dùng
     */
    @Transactional(readOnly = true)
    @Override
    public UserDTO getCurrentUser() throws Exception {
        String username = getUsernameOfCurrentLoginUser();
        return userMapper.toUserDTO(userRepository.findByUsername(username).orElseThrow(() -> new Exception("Người dùng không tồn tại")));
    }

    /**
     * Cập nhật thông tin người dùng hiện tại.
     *
     * @param createUserDTO Đối tượng CreateUserDTO chứa thông tin người dùng cần cập nhật
     * @return Đối tượng UserDTO đã được cập nhật
     * @throws Exception Nếu xảy ra lỗi trong quá trình cập nhật người dùng
     */
    @Transactional
    @Override
    public UserDTO updateCurrentUser(CreateUserDTO createUserDTO) throws Exception {
        String username = getUsernameOfCurrentLoginUser();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("Người dùng không tồn tại"));
        user.setPassword(bCryptPasswordEncoder.encode(createUserDTO.getPassword()));
        Optional<Role> role = roleRepository.findById(createUserDTO.getRoleDTO().getId());
        if (role.isPresent()) {
            createUserDTO.setRoleDTO(roleMapper.toDTO(role.get()));
        } else {
            throw new Exception("Không tìm thấy vai trò!");
        }
        user.setRole(role.get());
        user.setStatus(createUserDTO.isStatus());
        return userMapper.toUserDTO(userRepository.save(user));
    }

    /**
     * Tạo mới người dùng.
     *
     * @param createUserDTO Đối tượng CreateUserDTO chứa thông tin người dùng cần tạo mới
     * @return Đối tượng UserDTO đã được tạo mới
     * @throws Exception Nếu xảy ra lỗi trong quá trình tạo mới người dùng
     */
    @Transactional
    @Override
    public UserDTO create(CreateUserDTO createUserDTO) throws Exception {
        // Kiểm tra người dùng đã tồn tại chưa
        Optional<User> existedUser = userRepository.findByUsername(createUserDTO.getUsername());
        if (existedUser.isPresent()) {
            throw new Exception("Người dùng đã đăng ký trước đó!");
        }
        // Tạo người dùng mới
        createUserDTO.setPassword(bCryptPasswordEncoder.encode(createUserDTO.getPassword()));
        Optional<Role> role = roleRepository.findById(createUserDTO.getRoleDTO().getId());
        if (role.isPresent()) {
            createUserDTO.setRoleDTO(roleMapper.toDTO(role.get()));
        } else {
            throw new Exception("Không tìm thấy vai trò!");
        }
        User user = userMapper.toEntity(createUserDTO);
        user.setStatus(true);
        user = userRepository.save(user);
        Cart cart = new Cart(0, "new", user, null, null);
        if (role.get().getName().equals("ROLE_USER")) {
            cartService.save(cart);
        }
        for (Address address : user.getAddresses()) {
            address.setUser(user);
            addressRepository.save(address);
        }
        return userMapper.toUserDTO(user);
    }

    /**
     * Lấy tên người dùng của người dùng đăng nhập hiện tại.
     *
     * @return Tên người dùng của người dùng đăng nhập hiện tại
     * @throws Exception Nếu không tìm thấy tên người dùng
     */
    @Override
    public String getUsernameOfCurrentLoginUser() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username.equals("anonymousUser")) {
            throw new Exception("Vui lòng đăng nhập!");
        }
        return username;
    }
}
