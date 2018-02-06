package com.code.auth.support;

import com.code.auth.domain.PageBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class ModuleUtil {
    public static Sort DEFAULT_SORT = new Sort(Sort.DEFAULT_DIRECTION, "id");
    public static int DEFAULT_PAGE_NO = 1;
    public static int DEFAULT_PAGE_SIZE = 10;
    public static int MAX_PAGE_SIZE = 500;

    public static PageRequest toPageable(PageBean pageBean, Sort sort) {
        return new PageRequest(
                Math.max(0, pageBean.getPageNo() - 1),
                Math.min(MAX_PAGE_SIZE, pageBean.getPageSize() > 0 ? pageBean.getPageSize() : DEFAULT_PAGE_SIZE),
               sort != null ? sort:DEFAULT_SORT);
    }

}
