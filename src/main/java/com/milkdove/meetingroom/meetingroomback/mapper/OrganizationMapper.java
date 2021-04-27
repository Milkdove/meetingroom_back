package com.milkdove.meetingroom.meetingroomback.mapper;


import com.milkdove.meetingroom.meetingroomback.entites.Organization;
import com.milkdove.meetingroom.meetingroomback.entites.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


public interface OrganizationMapper extends Mapper<Organization> {

    @Select("select * from organization where organization_name=#{organization_name}")
    Organization queryUserbyName(@Param("organization_name") String organization_name);
}
