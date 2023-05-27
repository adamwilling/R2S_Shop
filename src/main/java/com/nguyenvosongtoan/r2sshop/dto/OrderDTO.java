package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDTO {
    private long id;
    private AddressDTO addressDTO;
    private UserDTO userDTO;
    private CartDTO cartDTO;
    private float totalPrice;
    private Date estimatedArrivalTime;
    private boolean isArrived;
}
