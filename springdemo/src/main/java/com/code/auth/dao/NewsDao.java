package com.code.auth.dao;

import com.code.auth.domain.News;
import com.code.auth.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface NewsDao extends PagingAndSortingRepository<News,Integer> {


}
