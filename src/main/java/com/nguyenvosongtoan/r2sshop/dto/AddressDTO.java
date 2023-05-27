package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private long id;
    private String address;
    private boolean isDeleted;
}
