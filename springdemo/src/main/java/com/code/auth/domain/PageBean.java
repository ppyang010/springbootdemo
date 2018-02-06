package com.code.auth.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PageBean implements Serializable{
    private int pageNo;
    private int pageSize;
    private int totalCount;
}
