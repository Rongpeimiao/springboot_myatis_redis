package com.springboot.rong.controller;


import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rong.entity.UserDTO;
import com.springboot.rong.redis.RedisUtil;
import com.springboot.rong.service.UserService;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

/**
 * 测试控制器
 */
@RestController
public class HelloController {

	 @Resource
	 private UserService userService;
	
	 @Autowired
	 private RedisUtil redisUtil;
	 
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!";
    }
    
    @RequestMapping("/showUser")
    public UserDTO toIndex(HttpServletRequest request, Model model){
        int userId = Integer.parseInt(request.getParameter("id"));
        UserDTO user = this.userService.getUserById(userId);
        
        HashMap<String, Object> maps=new HashMap<String, Object>();
        maps.put(user.getId().toString(), user.getUserName());
        maps.put(user.getId()+"1","容培淼。");
        redisUtil.hmset(user.getId().toString(), maps);
        return user;
    }
    
	/*Restful就是一个资源定位及资源操作的风格，不是标准也不是协议，只是一种风格，是对http协议的诠释。
	资源定位：互联网所有的事物都是资源，要求url中没有动词，只有名词，没有参数。url请求的风格就像这样：
	http://blog.csdn.net/eson_15/article/details/51743514
	@RequestMapping(value="/itemEdit/{id}")：{×××}表示占位符，请求的URL可以是“/itemEdit/1”或“/itemEdit/2”，
	通过在方法中使用@PathVariable获取{×××}中的×××变量。@PathVariable用于将请求URL中的模板变量映射到功能处理方法的参数上。
	如果@RequestMapping中表示为”/viewItems/{id}”，id和形参名称一致，那么@PathVariable就不用指定名称。 
	*/   
    
    @RequestMapping("/Restful/{id}")
    public UserDTO Restful(@PathVariable("id") Integer iid, Model model){
        UserDTO user = this.userService.getUserById(iid);
        return user;
    }
  
 
}