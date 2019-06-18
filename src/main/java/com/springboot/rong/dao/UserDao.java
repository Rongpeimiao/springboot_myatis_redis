package com.springboot.rong.dao;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.rong.entity.UserDTO;
@Mapper
public interface UserDao {
	int deleteByPrimaryKey(Integer id);

    int insert(UserDTO record);

    int insertSelective(UserDTO record);

    UserDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDTO record);

    int updateByPrimaryKey(UserDTO record);
}
