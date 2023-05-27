package com.nguyenvosongtoan.r2sshop.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nguyenvosongtoan.r2sshop.entity.Role;
import com.nguyenvosongtoan.r2sshop.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAccountDetail implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities; // ROLE_ADMIN, ROLE_USER
    private final Boolean status;

    public CustomAccountDetail(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.status = user.isStatus();

        // Tạo danh sách các GrantedAuthority từ vai trò của người dùng
        List<Role> roles = new ArrayList<>();
        roles.add(user.getRole());
        this.authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }

    // Trả về danh sách các quyền (GrantedAuthority) của người dùng
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Trả về mật khẩu của người dùng
    @Override
    public String getPassword() {
        return password;
    }

    // Trả về tên đăng nhập của người dùng
    @Override
    public String getUsername() {
        return username;
    }

    // Xác định xem tài khoản của người dùng có hết hạn hay không (luôn trả về true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Xác định xem tài khoản của người dùng có bị khóa hay không (luôn trả về true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Xác định xem thông tin xác thực của người dùng có hết hạn hay không (luôn trả về true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Xác định xem tài khoản của người dùng có được kích hoạt hay không
    @Override
    public boolean isEnabled() {
        return status;
    }
}
