package com.code.test;

import com.code.entity.UserInfo;
import com.code.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 演示取模的分库分表策略
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.code.mapper")
public class UserShardingTest {
	@Resource
	UserService userService;

	@Test
	public void insert(){
		userService.insert();
	}

	@Test
	public void select(){
//		for (int i =0;i<10;i++){
//			UserInfo userInfo1= userService.getUserInfoByUserId(1L);
//		}

		for (int i =0;i<10;i++){
			UserInfo userInfo1= userService.getUserInfoByUserIdForMaster(1L);
		}
	}

	@Test
	public void updateAndSelect(){

		userService.updateAndSelect(1L);

	}











/*	@Test
	public void selectByRange(){
		Map map = new HashMap<>();
		Long firstId = 10L;
		Long lastId = 20L;
		map.put(firstId, lastId);
		List<UserInfo> list = userService.selectByRange(10L,20L);
		System.out.println(list);
	}*/

}
