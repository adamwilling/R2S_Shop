package com.nguyenvosongtoan.r2sshop.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenvosongtoan.r2sshop.entity.*;
import com.nguyenvosongtoan.r2sshop.exception.NotFoundException;
import com.nguyenvosongtoan.r2sshop.repository.*;
import com.nguyenvosongtoan.r2sshop.service.*;
import com.nguyenvosongtoan.r2sshop.util.PaginationSortingUtils;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long key) {
        Optional<User> user = userRepository.findById(key);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException("Not found user");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll(int pageNo, int pageSize, String sortDir, String sortBy) {
        Pageable pageable = PaginationSortingUtils.getPageable(pageNo, pageSize, sortDir, sortBy);
        List<User> categories = userRepository.findAll(pageable).getContent();
        if (!categories.isEmpty()) {
            return categories;
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    @Override
    public User create(User user) {
    	user.setId(null);
        userRepository.save(user);
        return user;
    }

    @Transactional
    @Override
    public User update(User user) {
        Optional<User> existedCategory = userRepository.findById(user.getId());
        if (existedCategory.isPresent()) {
            userRepository.save(user);
            return user;
        } else {
            throw new NotFoundException("Not found user");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<User> existedCategory = userRepository.findById(id);
        if (existedCategory.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found user");
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            return new CustomUserDetail(user.get());
        } else {
            throw new NotFoundException("User not found");
        }
    }
}