package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends CreateUserDTO{
    private long id;
}
