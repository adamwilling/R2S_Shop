package com.nguyenvosongtoan.r2sshop.mapper;

import org.mapstruct.Mapper;

import com.nguyenvosongtoan.r2sshop.dto.AddressDTO;
import com.nguyenvosongtoan.r2sshop.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toEntity(AddressDTO addressDTO);

    AddressDTO toDTO(Address address);
}
