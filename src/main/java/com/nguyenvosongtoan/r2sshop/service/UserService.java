package com.nguyenvosongtoan.r2sshop.service;

import com.nguyenvosongtoan.r2sshop.entity.User;

public interface UserService extends BaseService<User, Long> {
	void delete(Long id);
}