package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

import com.nguyenvosongtoan.r2sshop.dto.AddressDTO;
import com.nguyenvosongtoan.r2sshop.entity.Address;

public interface AddressService {
	
	Address findById(long id) throws Exception;

	List<AddressDTO> getAllAddressFromCurrentUser() throws Exception;

	List<AddressDTO> create(List<AddressDTO> addressDTOList) throws Exception;

	List<AddressDTO> update(List<AddressDTO> addressDTOList) throws Exception;

	String delete(String listId) throws Exception;
	
}
