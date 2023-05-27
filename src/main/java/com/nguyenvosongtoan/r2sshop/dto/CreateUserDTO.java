package com.nguyenvosongtoan.r2sshop.dto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String gender;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private RoleDTO roleDTO;
    private List<AddressDTO> addressDTOS;
    private boolean status;
}