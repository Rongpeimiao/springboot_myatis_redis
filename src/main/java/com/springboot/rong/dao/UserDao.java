package com.springboot.rong.dao;

import java.util.ArrayList;
import java.util.Map;

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
    
    ArrayList<UserDTO> selectUserDTOList(Map<String,Object> maps);
    
    //通过username查询用户
   // SelfUserDetails getUser(String username);
}
