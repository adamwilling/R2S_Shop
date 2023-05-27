package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.AddressDTO;
import com.nguyenvosongtoan.r2sshop.entity.Address;
import com.nguyenvosongtoan.r2sshop.exception.AddressNotFoundException;

public interface AddressService {
	
	Address findById(long id) throws AddressNotFoundException;

	List<AddressDTO> getAllAddressFromCurrentUser() throws AddressNotFoundException;

	List<AddressDTO> create(List<AddressDTO> addressDTOList) throws AddressNotFoundException;

	List<AddressDTO> update(List<AddressDTO> addressDTOList) throws AddressNotFoundException;

	String delete(String listId) throws Exception;
	
}
