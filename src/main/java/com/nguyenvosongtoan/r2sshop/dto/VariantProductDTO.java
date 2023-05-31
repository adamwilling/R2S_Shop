package com.nguyenvosongtoan.r2sshop.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VariantProductDTO extends VariantProCreateDTO{
    private long id;
    List<CartLineItemDTO> cartLineItemDTOs;
}
