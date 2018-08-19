package com.code.auth.service.impl;

import com.code.auth.dao.NewsDao;
import com.code.auth.domain.News;
import com.code.auth.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author ccy
 */
@Service
@AllArgsConstructor
public class NewServiceImpl implements NewsService {
    private NewsDao newsDao;
    @Override
    public void init(int num) {
//        long count = newsDao.count();
        int randomId = Double.valueOf(Math.random() * 100000).intValue() ;
        News one = newsDao.findOne(randomId);
        if(Objects.nonNull(one)){

            List<News> list = new ArrayList(num);
            for(int i = 0;i < num;i++){
                News save = new News();
                BeanUtils.copyProperties(one, save);
                save.setNid(null);
                save.setTitle(save.getTitle() + randomId);
                save.setCount(randomId);
                save.setEdit("ccy" + randomId);
                save.setTime(new Date());
                list.add(save);
            }
            newsDao.save(list);
        }
    }
}
