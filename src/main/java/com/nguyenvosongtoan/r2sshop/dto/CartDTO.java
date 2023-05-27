package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private long id;
    private String status;
    private UserDTO user;
    private List<CartLineItemDTO> cartLineItemDTOs;
}
