package com.nguyenvosongtoan.r2sshop.service;

import com.nguyenvosongtoan.r2sshop.dto.OrderACartDTO;
import com.nguyenvosongtoan.r2sshop.dto.OrderDTO;
import com.nguyenvosongtoan.r2sshop.exception.OrderNotFoundException;

public interface OrderService {
	
    OrderDTO findById(long id) throws OrderNotFoundException;

    OrderDTO order(OrderACartDTO orderACartDTO) throws Exception;
    
}
