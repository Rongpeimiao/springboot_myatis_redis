package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.rong.DemoApplication;
import com.springboot.rong.entity.UserDTO;
import com.springboot.rong.service.UserService;
import com.springboot.rong.until.redis.RedisUtil;
import com.sun.tools.javac.util.List;
import com.github.pagehelper.PageHelper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=DemoApplication.class)
@EnableAutoConfiguration
public class DemoApplicationTests {
	 @Autowired
	 UserService userService;
	
	 @Autowired
	  RedisUtil redisUtil;
	 
	@Test
	public void contextLoads() {
		System.out.println("test1");
	}
	
	@Test
	public void testDataSources(){
		 /* UserDTO user = this.userService.getUserById(20);
		  System.out.println(user.toString());*/
		Map<String, Object> maps=new HashMap<String, Object>();
		PageHelper.startPage(1,5);
		ArrayList<UserDTO> listUserDTO= userService.selectUserDTOList(maps);
		  for(int i=0;i<listUserDTO.size();i++){
			  System.out.println(listUserDTO.get(i).getUserName());
		  }
	}
	@Test
	public void testRedisSources(){
		 //测试Redis
		  String testString =(String) redisUtil.hget("20","20");
		  //System.out.println(testString);
		  Map<Object, Object> hmap= redisUtil.hmget("20");
		  //System.out.println(hmap.get("201"));
		  //System.out.println(redisUtil.hasKey("20"));
		  //redisUtil.set("setTest", "setTest",10000);
		  //System.out.println(redisUtil.get("setTest"));
		  //redisUtil.set("21", 1);
		 // System.out.println(redisUtil.get("21"));
		 // System.out.println(redisUtil.incr("21",1));
		  //System.out.println(redisUtil.get("21"));
		  //redisUtil.hset("20","202","hset");
		  //redisUtil.sSet("sSet",1,2,3,4);
		  
		  //测试Service
		 // System.out.println(userService.getUserById(20).getUserName());
		  //ZSet 操作
		/*  System.out.println(redisUtil.gZSet("111",2,6)); 
		  Set<Object>  gZSet=  redisUtil.gZSet("111",2,6);
		  Object[] arr;
		  arr=gZSet.toArray();
		  for(int i=0;i<arr.length;i++){
			  System.out.println(arr[i] instanceof HashMap); 
		  }
		  HashMap hm=(HashMap) arr[2];
		 System.out.println(hm.get("201")); */
		  //list操作
		  
		  System.out.println(redisUtil.lGetListSize("userList"));
		  
	}
	
	@Test
	public void testInertUser(){
		ArrayList<UserDTO> listu=new ArrayList<UserDTO>();
		for(int i=22;i<25;i++){
			UserDTO user=new UserDTO();
			user.setId(i+1);
			user.setUserName("rong"+i+1);
			user.setPassword("123");
			userService.addUser(user);
			listu.add(user);
		}
		 redisUtil.lSet("userList",listu,10000);
	}
}
