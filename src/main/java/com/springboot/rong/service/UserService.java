package com.springboot.rong.service;

import java.util.ArrayList;
import java.util.Map;

import com.springboot.rong.entity.UserDTO;

public interface UserService {
	 public UserDTO getUserById(int userId);
	 boolean addUser(UserDTO record);
	 
	 ArrayList<UserDTO> selectUserDTOList(Map<String,Object> maps);
}
