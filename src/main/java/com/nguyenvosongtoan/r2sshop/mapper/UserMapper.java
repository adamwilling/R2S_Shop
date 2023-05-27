package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenvosongtoan.r2sshop.dto.CreateUserDTO;
import com.nguyenvosongtoan.r2sshop.dto.UserDTO;
import com.nguyenvosongtoan.r2sshop.entity.User;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, AddressMapper.class})
public interface UserMapper {
    @Mapping(source = "addressDTOS", target = "addresses")
    @Mapping(source = "roleDTO", target = "role")
    User toEntity(CreateUserDTO createUserDTO);
    @Mapping(source = "addresses", target = "addressDTOS")
    @Mapping(source = "role", target = "roleDTO")
    CreateUserDTO toCreateUserDTO(User user);

    @Mapping(source = "addresses", target = "addressDTOS")
    @Mapping(source = "role", target = "roleDTO")
    UserDTO toUserDTO(User user);

    @Mapping(source = "addressDTOS", target = "addresses")
    @Mapping(source = "roleDTO", target = "role")
    User toEntity(UserDTO userDTO);
}
