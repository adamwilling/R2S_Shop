package com.nguyenvosongtoan.r2sshop.service;

import java.util.List;

public interface BaseService <T, K> {
    T getById(K id);
    List<T> getAll(int page, int limit, String sort, String order);
    T create(T type);
    T update(T type);
	void delete(K id);
}

