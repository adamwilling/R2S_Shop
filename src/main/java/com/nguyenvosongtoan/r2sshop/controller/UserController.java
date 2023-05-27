package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nguyenvosongtoan.r2sshop.constant.ApiUrlConstant;
import com.nguyenvosongtoan.r2sshop.dto.CreateUserDTO;
import com.nguyenvosongtoan.r2sshop.service.UserService;

@RequestMapping(ApiUrlConstant.USERS)
@RestController
public class UserController {

    private final UserService userService;

    // Constructor DI để Inject UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getCurrentUser() {
        try {
            return ResponseEntity.ok(userService.getCurrentUser());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    ResponseEntity<?> updateCurrentUser(@RequestBody CreateUserDTO createUserDTO) {
        try {
            return ResponseEntity.ok(userService.updateCurrentUser(createUserDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserDTO createUserDTO) {
        try {
            return ResponseEntity.ok(userService.create(createUserDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
