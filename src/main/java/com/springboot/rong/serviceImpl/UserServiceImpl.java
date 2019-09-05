package com.springboot.rong.serviceImpl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rong.dao.UserDao;
import com.springboot.rong.entity.UserDTO;
import com.springboot.rong.until.redis.RedisUtil;
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
		try {
			String name= (String) redisUtil.hget(String.valueOf(userId) ,String.valueOf(userId) );
			if(null!=name&&!name.isEmpty()){
				udto.setId(2011);
				udto.setUserName(name);
				return udto;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("redi查询失败！");
		}
		try {
			udto= userDao.selectByPrimaryKey(userId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("mysql数据库查询失败！");
		}
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

	@Override
	public ArrayList<UserDTO> selectUserDTOList(Map<String, Object> maps) {

		
		return userDao.selectUserDTOList(maps);
	}


}
