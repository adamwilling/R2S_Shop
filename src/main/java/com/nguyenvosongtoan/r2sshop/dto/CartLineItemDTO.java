package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CartLineItemDTO extends AddCartLineItemDTO{
    private long id;
    private boolean isDeleted;
    private CartDTO cartDTO;
}
