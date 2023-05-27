package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenvosongtoan.r2sshop.dto.OrderDTO;
import com.nguyenvosongtoan.r2sshop.entity.Order;

@Mapper(componentModel = "spring",uses = {AddressMapper.class, UserMapper.class})
public interface OrderMapper {
    @Mapping(source = "addressDTO", target = "address")
    @Mapping(source = "userDTO", target = "user")
    @Mapping(source = "cartDTO", target = "cart")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(source = "address", target = "addressDTO")
    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "cart", target = "cartDTO")
    OrderDTO toDTO(Order order);
}
