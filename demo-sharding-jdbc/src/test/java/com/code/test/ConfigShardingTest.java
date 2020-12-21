package com.code.test;

import com.code.entity.Config;
import com.code.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 广播表的分库分表策略
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.code.mapper")
public class ConfigShardingTest {
    @Resource
    ConfigService configService;

    @Test
    public void insert(){

        configService.insert();
    }

    @Test
    public void update(){

        configService.update(1);
    }

    @Test
    public void select(){
        for(int i=0;i<3;i++){
            Config config1 = configService.geConfigById(1);
        }
    }

}

