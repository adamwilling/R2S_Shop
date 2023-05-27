package com.nguyenvosongtoan.r2sshop.service;

import com.nguyenvosongtoan.r2sshop.dto.OrderACartDTO;
import com.nguyenvosongtoan.r2sshop.dto.OrderDTO;

public interface OrderService {
	
    OrderDTO findById(long id) throws Exception;

    OrderDTO order(OrderACartDTO orderACartDTO) throws Exception;
    
}
