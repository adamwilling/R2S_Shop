package com.nguyenvosongtoan.r2sshop.entity;

import lombok.*;

@Data
@Getter
@Setter
public class SigninRequest {
	private String username;
	private String password;
}
