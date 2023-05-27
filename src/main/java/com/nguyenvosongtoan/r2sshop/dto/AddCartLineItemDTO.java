package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;

@Data
public class AddCartLineItemDTO {
    private float price;
    private int amount;
    private ProductDTO productDTO;
}
