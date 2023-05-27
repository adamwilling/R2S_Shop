package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenvosongtoan.r2sshop.constant.ApiUrlConstant;
import com.nguyenvosongtoan.r2sshop.dto.LoginRequestDTO;
import com.nguyenvosongtoan.r2sshop.dto.ResponseLoginDTO;
import com.nguyenvosongtoan.r2sshop.util.JwtUtils;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.LOGIN)
public class LoginController {

    private final AuthenticationManager authenticationManager;

    // Constructor DI để Inject AuthenticationManager
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> generateToken(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            // Xác thực thông tin đăng nhập
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(), loginRequestDTO.getPassword()
                    ));
            if (authentication.isAuthenticated()) {
                ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO();
                responseLoginDTO.setUsername(loginRequestDTO.getUsername());
                responseLoginDTO.setToken("Bearer " + JwtUtils.generateToken(loginRequestDTO.getUsername()));
                List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
                responseLoginDTO.setRole(roles.get(0));
                return new ResponseEntity<>(responseLoginDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Đăng nhập thất bại!", HttpStatus.BAD_GATEWAY);
        }
        return null;
    }
}
