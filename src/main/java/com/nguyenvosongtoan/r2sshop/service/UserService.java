package com.nguyenvosongtoan.r2sshop.service;


import com.nguyenvosongtoan.r2sshop.dto.CreateUserDTO;
import com.nguyenvosongtoan.r2sshop.dto.UserDTO;

public interface UserService {
	
    public UserDTO create(CreateUserDTO createUserDTO) throws Exception;

    UserDTO getCurrentUser() throws Exception;

    UserDTO updateCurrentUser(CreateUserDTO createUserDTO) throws Exception;

    String getUsernameOfCurrentLoginUser() throws Exception;
    
}
