package com.yh.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yh.community.model.User;

@Mapper
public interface UserMapper { 
	@Insert("insert into COMMUNITY.PUBLIC.\"user\" (NAME, ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
	void insert(User user);
	
	@Select("SELECT * FROM COMMUNITY.PUBLIC.\"user\" WHERE TOKEN=#{token}")
	User findByToken(@Param("token") String token);
	
}
