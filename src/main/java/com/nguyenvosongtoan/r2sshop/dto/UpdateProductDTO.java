package com.nguyenvosongtoan.r2sshop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductDTO extends ProductCreateDTO{
    private long id;

}
