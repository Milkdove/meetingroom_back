package com.milkdove.meetingroom.meetingroomback.service;

import com.github.pagehelper.Page;
import com.milkdove.meetingroom.meetingroomback.entites.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 根据查询条件的分页查询接口
 *
 */
public interface UserService {
    List<Users> queryUsersByModel(Users users, int page, int rows);
    List<Users> queryUsersByOrgId(Integer organization_id, int page, int rows);
    List<Users> queryUsersByMobile(String user_mobile, int page, int rows);
    List<Users> queryUsersByName(String user_name, int page, int rows);
}
