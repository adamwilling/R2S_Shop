package com.nguyenvosongtoan.r2sshop.entity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetail implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities; //ROLE_ADMIN, ROLE_USER
    private final Boolean status;

    public CustomUserDetail(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.status = user.getStatus();
        authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
