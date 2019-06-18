package com.springboot.rong.service;

import com.springboot.rong.entity.UserDTO;

public interface UserService {
	 public UserDTO getUserById(int userId);
	 boolean addUser(UserDTO record);
}
