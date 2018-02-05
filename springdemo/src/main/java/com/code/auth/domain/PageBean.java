package com.code.auth.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageBean {
    private int pageNo;
    private int pageSize;
    private int totalCount;
}
