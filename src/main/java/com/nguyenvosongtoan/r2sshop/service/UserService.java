package com.nguyenvosongtoan.r2sshop.service;


import com.nguyenvosongtoan.r2sshop.dto.CreateUserDTO;
import com.nguyenvosongtoan.r2sshop.dto.UserDTO;
import com.nguyenvosongtoan.r2sshop.exception.UserNotFoundException;

public interface UserService {
	
    public UserDTO create(CreateUserDTO createUserDTO) throws Exception;

    UserDTO getCurrentUser() throws UserNotFoundException;

    UserDTO updateCurrentUser(CreateUserDTO createUserDTO) throws UserNotFoundException;

    String getUsernameOfCurrentLoginUser() throws UserNotFoundException;
    
}
