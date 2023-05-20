package com.nguyenvosongtoan.r2sshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenvosongtoan.r2sshop.constant.PaginationConstant;
import com.nguyenvosongtoan.r2sshop.entity.Address;
import com.nguyenvosongtoan.r2sshop.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
	public ResponseEntity<List<Address>> getAll(
			@RequestParam(value = "page", defaultValue = PaginationConstant.DEFAULT_PAGE_NUMBER, required = false) int page,
			@RequestParam(value = "limit", defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE, required = false) int limit,
			@RequestParam(value = "sort", defaultValue = PaginationConstant.DEFAULT_SORT_DIRECTION, required = false) String sort,
			@RequestParam(value = "order", defaultValue = PaginationConstant.DEFAULT_SORT_BY, required = false) String order) {
		List<Address> addresses = addressService.getAll(page, limit, sort, order);
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Address> getById(@PathVariable("id") Long id) {
		Address address = addressService.getById(id);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Address> create(@RequestBody Address address) {
		Address result = addressService.create(address);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Address> update(@RequestBody Address address) {
		Address result = addressService.update(address);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		addressService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Address>> getAllByUserId(@PathVariable Long userId,
			@RequestParam(value = "page", defaultValue = PaginationConstant.DEFAULT_PAGE_NUMBER, required = false) int page,
			@RequestParam(value = "limit", defaultValue = PaginationConstant.DEFAULT_PAGE_SIZE, required = false) int limit,
			@RequestParam(value = "sort", defaultValue = PaginationConstant.DEFAULT_SORT_DIRECTION, required = false) String sort,
			@RequestParam(value = "order", defaultValue = PaginationConstant.DEFAULT_SORT_BY, required = false) String order) {
		List<Address> addresses = addressService.getAddressesByUserId(userId, page, limit, sort, order);
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}
}