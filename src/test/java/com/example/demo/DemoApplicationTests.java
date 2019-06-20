package com.example.demo;

import java.util.HashMap;
import java.util.Map;

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
		  UserDTO user = this.userService.getUserById(20);
		  System.out.println(user.toString());
	}
	@Test
	public void testRedisSources(){
		  String testString =(String) redisUtil.hget("20","20");
		  System.out.println(testString);
		  Map<Object, Object> hmap= redisUtil.hmget("20");
		  System.out.println(hmap.get("201"));
		  System.out.println(redisUtil.hasKey("20"));
		  //redisUtil.set("setTest", "setTest",10000);
		  System.out.println(redisUtil.get("setTest"));
		  redisUtil.set("21", 1);
		  System.out.println(redisUtil.get("21"));
		  System.out.println(redisUtil.incr("21",1));
		  System.out.println(redisUtil.get("21"));
		  //redisUtil.hset("20","202","hset");
		  //redisUtil.sSet("sSet",1,2,3,4);
		  System.out.println( userService.getUserById(20).getUserName());
	}
}
