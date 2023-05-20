package com.nguyenvosongtoan.r2sshop.service.impl;

import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.entity.Address;
import com.nguyenvosongtoan.r2sshop.exception.NotFoundException;
import com.nguyenvosongtoan.r2sshop.repository.AddressRepository;
import com.nguyenvosongtoan.r2sshop.service.AddressService;
import com.nguyenvosongtoan.r2sshop.util.PaginationSortingUtils;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Address getById(Long key) {
        Optional<Address> address = addressRepository.findById(key);
        if (address.isPresent()) {
            return address.get();
        } else {
            throw new NotFoundException("Not found address");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Address> getAll(int page, int limit, String sort, String order) {
        Pageable pageable = PaginationSortingUtils.getPageable(page, limit, sort, order);
        List<Address> addresses = addressRepository.findAll(pageable).getContent();
        if (!addresses.isEmpty()) {
            return addresses;
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    @Override
    public Address create(Address address) {
    	address.setId(null);
        addressRepository.save(address);
        return address;
    }

    @Transactional
    @Override
    public Address update(Address address) {
        Optional<Address> existedAddress = addressRepository.findById(address.getId());
        if (existedAddress.isPresent()) {
            addressRepository.save(address);
            return address;
        } else {
            throw new NotFoundException("Not found address");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Address> existedAddress = addressRepository.findById(id);
        if (existedAddress.isPresent()) {
            addressRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found address");
        }
    }

    @Transactional(readOnly = true)
	@Override
	public List<Address> getAddressesByUserId(Long userId, int page, int limit, String sort, String order) {
        Pageable pageable = PaginationSortingUtils.getPageable(page, limit, sort, order);
        List<Address> addresses = addressRepository.findByUserId(userId, pageable).getContent();
        if (!addresses.isEmpty()) {
            return addresses;
        } else {
            return Collections.emptyList();
        }
	}
}