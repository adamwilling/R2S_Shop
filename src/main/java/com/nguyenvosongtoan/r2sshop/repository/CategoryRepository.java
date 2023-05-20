package com.nguyenvosongtoan.r2sshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenvosongtoan.r2sshop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}