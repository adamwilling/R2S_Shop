package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;

import com.nguyenvosongtoan.r2sshop.dto.RoleDTO;
import com.nguyenvosongtoan.r2sshop.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);
}
