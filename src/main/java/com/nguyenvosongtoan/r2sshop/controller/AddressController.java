package com.nguyenvosongtoan.r2sshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nguyenvosongtoan.r2sshop.constant.ApiUrlConstant;
import com.nguyenvosongtoan.r2sshop.dto.AddressDTO;
import com.nguyenvosongtoan.r2sshop.service.AddressService;

import java.util.List;

@RequestMapping(ApiUrlConstant.ADDRESSES)
@RestController
public class AddressController {
	
    private final AddressService addressService;

    // Constructor DI để Inject AddressService
    public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
    public ResponseEntity<?> getAllAddressFromCurrentUser(){
        try {
            return ResponseEntity.ok(addressService.getAllAddressFromCurrentUser());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<AddressDTO> addressDTOList){
        try {
            return ResponseEntity.ok(addressService.create(addressDTOList));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody List<AddressDTO> addressDTOList){
        try {
            return ResponseEntity.ok(addressService.update(addressDTOList));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String listID){
        try {
            return ResponseEntity.ok(addressService.delete(listID));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
