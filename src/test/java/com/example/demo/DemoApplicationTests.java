package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.rong.DemoApplication;
import com.springboot.rong.entity.UserDTO;
import com.springboot.rong.redis.RedisUtil;
import com.springboot.rong.service.UserService;
import com.sun.tools.javac.util.List;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


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
		  
		  System.out.println(redisUtil.gZSet("111",3,6)); 
		  List<Object> gZSet=  (List<Object>) redisUtil.gZSet("111",3,6);
		 
		 System.out.println(gZSet.get(0)); 
	}
	
	@Test
	public void testInertUser(){
		for(int i=22;i<25;i++){
			UserDTO user=new UserDTO();
			user.setId(i+1);
			user.setUserName("rong"+i+1);
			user.setPassword("123");
			userService.addUser(user);
		}
	}
}
