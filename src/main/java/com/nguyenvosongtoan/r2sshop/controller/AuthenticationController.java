package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenvosongtoan.r2sshop.entity.SigninRequest;
import com.nguyenvosongtoan.r2sshop.entity.User;
import com.nguyenvosongtoan.r2sshop.service.UserService;
import com.nguyenvosongtoan.r2sshop.util.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SigninRequest signinRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                		signinRequest.getUsername(), signinRequest.getPassword()
                ));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(JwtUtils.generateToken(signinRequest.getUsername()), HttpStatus.OK);
        }
        return null;
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
    	User createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }
}
