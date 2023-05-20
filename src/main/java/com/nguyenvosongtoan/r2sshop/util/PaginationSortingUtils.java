package com.nguyenvosongtoan.r2sshop.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationSortingUtils {
    public static Pageable getPageable(int page, int limit, String sort, String order) {
        Sort sorts = sort.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(order).ascending() : Sort.by(order).descending();
        Pageable pageable = PageRequest.of(page, limit, sorts);
        return pageable;
    }
}
