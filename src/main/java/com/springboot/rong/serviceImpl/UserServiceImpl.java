package com.springboot.rong.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.rong.dao.UserDao;
import com.springboot.rong.entity.UserDTO;
import com.springboot.rong.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
    private UserDao userDao;

	@Override
	public UserDTO getUserById(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public boolean addUser(UserDTO record) {
		boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
	}


}
