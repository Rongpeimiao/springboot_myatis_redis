package com.springboot.rong.service;

import java.util.ArrayList;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.springboot.rong.entity.UserDTO;

public interface UserService {
	 public UserDTO getUserById(int userId);
	 boolean addUser(UserDTO record);
	 
	 ArrayList<UserDTO> selectUserDTOList(Map<String,Object> maps);
}
