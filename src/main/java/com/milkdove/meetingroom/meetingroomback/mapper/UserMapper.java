package com.milkdove.meetingroom.meetingroomback.mapper;

import com.github.pagehelper.Page;

import com.milkdove.meetingroom.meetingroomback.entites.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.lang.Nullable;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<Users>{

    /**
     * 根据名称查询
     * @param user_name
     * @return
     */
    @Select("select * from users where user_name=#{user_name}")
    Users queryUserbyName(@Param("user_name") String user_name);

    @Select("select * from users where user_id=#{user_id}")
    Users queryUserbyId(@Param("user_id")Integer user_id);

    @Update("update users set user_state = #{user_state} where user_id=#{user_id}")
    Integer updateStatebyId(@Param("user_id")Integer user_id,@Param("user_state")Integer user_state);

    @Update("update users set organization_id=#{organization_id} where user_id=#{user_id}")
    Integer joinOrg(@Param("user_id")Integer user_id, @Param("organization_id")Integer organization_id);
}
