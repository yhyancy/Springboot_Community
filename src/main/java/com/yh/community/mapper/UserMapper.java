package com.yh.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.yh.community.model.User;

@Mapper
public interface UserMapper { 
	@Insert("insert into COMMUNITY.PUBLIC.\"user\" (NAME, ACCOUNT_ID,TOKEN,GMT_CREATE,GMT_MODIFIED) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
	void insert(User user);
}
