package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;

@Data
public class ResponseLoginDTO {
    private String username;
    private String token;
    private String role;
}
