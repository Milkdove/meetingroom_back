package com.milkdove.meetingroom.meetingroomback.mapper;

import com.milkdove.meetingroom.meetingroomback.entites.Meeting;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface MeetingMapper  extends Mapper<Meeting> {


    /**
     * 通过组织ID查询会议申请
     * @param organization_id
     * @return
     */
    @Select("select * from meeting where organization_id = #{organization_id}")
    List<Meeting> selectByOrg(@Param("organization_id") Integer organization_id);

    /**
     * 修改状态
     * @param meeting_id
     * @param meeting_state
     * @return
     */
    @Update("Update meeting set meeting_state = #{meeting_state} where meeting_id = #{meeting_id}")
    int updateStateById(@Param("meeting_id") Integer meeting_id, @Param("meeting_state") Integer meeting_state);

    /**
     * 通过使用者ID查询会议申请
     * @param user_id
     * @return
     */
    @Select("select * from meeting where user_id = #{user_id}")
    List<Meeting> selectByuser_id(@Param("user_id") Integer user_id);


    /**
     * 通过管理者ID查询会议申请
     * @param admin_id
     * @return
     */
    @Select("select * from meeting where admin_id = #{admin_id}")
    List<Meeting> selectByadmin_id(@Param("admin_id") Integer admin_id);

    /**
     * 通过会议名称查询会议申请
     * @param meeting_name
     * @return
     */
    @Select("select * from meeting where meeting_name = #{meeting_name} and organization_id=#{organization_id}")
    Meeting selectByName(@Param("meeting_name") String meeting_name,@Param("organization_id") Integer organization_id);
}
