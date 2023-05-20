package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.entity.Address;

public interface AddressService extends BaseService<Address, Long> {
    List<Address> getAddressesByUserId(Long userId, int page, int limit, String sort, String order);
}