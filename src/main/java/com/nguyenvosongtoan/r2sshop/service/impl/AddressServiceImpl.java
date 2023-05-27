package com.nguyenvosongtoan.r2sshop.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.dto.AddressDTO;
import com.nguyenvosongtoan.r2sshop.entity.Address;
import com.nguyenvosongtoan.r2sshop.entity.User;
import com.nguyenvosongtoan.r2sshop.exception.AddressNotFoundException;
import com.nguyenvosongtoan.r2sshop.exception.UserNotFoundException;
import com.nguyenvosongtoan.r2sshop.mapper.AddressMapper;
import com.nguyenvosongtoan.r2sshop.mapper.UserMapper;
import com.nguyenvosongtoan.r2sshop.repository.AddressRepository;
import com.nguyenvosongtoan.r2sshop.service.AddressService;
import com.nguyenvosongtoan.r2sshop.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    // Constructor DI để Inject các dependency
    public AddressServiceImpl(AddressRepository addressRepository,@Lazy UserService userService, UserMapper userMapper, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.userService = userService;
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    /**
     * Tìm địa chỉ theo ID.
     *
     * @param id ID của địa chỉ cần tìm
     * @return Đối tượng Address nếu tìm thấy
     * @throws Exception Ném ngoại lệ nếu địa chỉ không tồn tại
     */
    @Transactional(readOnly = true)
    @Override
    public Address findById(long id) throws AddressNotFoundException {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException("Địa chỉ không tồn tại!"));
    }

    /**
     * Lấy danh sách địa chỉ của người dùng hiện tại.
     *
     * @return Danh sách AddressDTO
     * @throws Exception Ném ngoại lệ nếu không lấy được danh sách địa chỉ
     */
    @Transactional(readOnly = true)
    @Override
    public List<AddressDTO> getAllAddressFromCurrentUser() throws AddressNotFoundException {
        return userService.getCurrentUser().getAddressDTOS();
    }

    /**
     * Tạo mới danh sách địa chỉ.
     *
     * @param addressDTOList Danh sách AddressDTO cần tạo mới
     * @return Danh sách AddressDTO đã được tạo mới
     * @throws Exception Ném ngoại lệ nếu có lỗi xảy ra trong quá trình tạo mới
     */
    @Transactional
    @Override
    public List<AddressDTO> create(List<AddressDTO> addressDTOList) throws UserNotFoundException {
        User user = userMapper.toEntity(userService.getCurrentUser());
        List<AddressDTO> addressDTOS = new ArrayList<>();
        for (AddressDTO addressDTO : addressDTOList) {
            Address address = addressMapper.toEntity(addressDTO);
            address.setId(0);
            address.setUser(user);
            addressDTOS.add(addressMapper.toDTO(addressRepository.save(address)));
        }
        return addressDTOS;
    }

    /**
     * Cập nhật danh sách địa chỉ.
     *
     * @param addressDTOList Danh sách AddressDTO cần cập nhật
     * @return Danh sách AddressDTO đã được cập nhật
     * @throws Exception Ném ngoại lệ nếu có lỗi xảy ra trong quá trình cập nhật
     */
    @Transactional
    @Override
    public List<AddressDTO> update(List<AddressDTO> addressDTOList) throws AddressNotFoundException {
        User user = userMapper.toEntity(userService.getCurrentUser());
        List<Address> addressList = new ArrayList<>();
        for (AddressDTO addressDTO : addressDTOList) {
            if (addressDTO.getId() == 0)
                throw new AddressNotFoundException("Không có ID của địa chỉ!");
            if (!checkAddressWithUser(addressDTO.getId(), user.getUsername()))
                throw new AddressNotFoundException("Người dùng hiện tại không có ID địa chỉ này!");
            Address address = addressMapper.toEntity(addressDTO);
            address.setUser(user);
            addressList.add(address);
        }
        return addressRepository.saveAll(addressList).stream().map(address -> addressMapper.toDTO(address)).collect(Collectors.toList());
    }

    /**
     * Xóa danh sách địa chỉ.
     *
     * @param listId Chuỗi ID của các địa chỉ cần xóa, phân tách bằng dấu phẩy (,)
     * @return Chuỗi thông báo xóa thành công
     * @throws Exception Ném ngoại lệ nếu có lỗi xảy ra trong quá trình xóa
     */
    @Transactional
    @Override
    public String delete(String listId) throws Exception {
        User user = userMapper.toEntity(userService.getCurrentUser());
        List<Long> listAddressId = Arrays.stream(listId.split(",")).map(Long::parseLong).toList();
        for (Long id : listAddressId) {
            Address address = addressRepository.findById(id).orElseThrow(() -> new Exception("Không có ID địa chỉ!"));
            if (!checkAddressWithUser(address.getId(), user.getUsername()))
                throw new Exception("Người dùng hiện tại không có ID địa chỉ này!");
            address.setDeleted(true);
            addressRepository.save(address);
        }
        return "Xóa thành công!";
    }

    /**
     * Kiểm tra địa chỉ có thuộc về người dùng hiện tại hay không.
     *
     * @param addressId ID của địa chỉ
     * @param username  Tên đăng nhập của người dùng
     * @return true nếu địa chỉ thuộc về người dùng hiện tại, false nếu không thuộc về
     */
    private boolean checkAddressWithUser(long addressId, String username) {
        Address address = addressRepository.findById(addressId).orElseThrow();
        return address.getUser().getUsername().equals(username);
    }
}
