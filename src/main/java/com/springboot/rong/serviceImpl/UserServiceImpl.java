package com.springboot.rong.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rong.dao.UserDao;
import com.springboot.rong.entity.UserDTO;
import com.springboot.rong.redis.RedisUtil;
import com.springboot.rong.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
    private UserDao userDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDTO getUserById(int userId) {
		UserDTO udto=new UserDTO();
	    String name= (String) redisUtil.hget(String.valueOf(userId) ,String.valueOf(userId) );
        if(!name.isEmpty()){
        	udto.setId(2011);
        	udto.setUserName(name);
        	return udto;
        }
        udto= userDao.selectByPrimaryKey(userId);
		return udto;
	}

	@Override
	public boolean addUser(UserDTO record) {
		boolean result = false;
        try {
        	/* HashMap<String, Object> maps=new HashMap<String, Object>();
            maps.put(user.getId().toString(), record.getUserName());
            maps.put(user.getId()+"1","容培淼。");
            redisUtil.hmset(user.getId().toString(), maps);*/
        	
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
	}


}
