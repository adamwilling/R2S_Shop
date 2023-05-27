package com.nguyenvosongtoan.r2sshop.config;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nguyenvosongtoan.r2sshop.entity.User;
import com.nguyenvosongtoan.r2sshop.exception.AccountNotFoundException;
import com.nguyenvosongtoan.r2sshop.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomAccountServiceDetail implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomAccountServiceDetail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Phương thức này được gọi khi người dùng cố gắng xác thực bằng tên đăng nhập
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm kiếm người dùng theo tên đăng nhập
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            // Nếu người dùng tồn tại, tạo một đối tượng CustomAccountDetail từ người dùng đó và trả về
            return new CustomAccountDetail(user.get());
        } else {
            // Nếu không tìm thấy người dùng, ném ngoại lệ UsernameNotFoundException
            throw new AccountNotFoundException("Account not found");
        }
    }
}
