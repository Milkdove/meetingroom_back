package com.milkdove.meetingroom.meetingroomback.mapper;

import com.milkdove.meetingroom.meetingroomback.entites.Room;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoomMapper extends Mapper<Room> {


    @Select("select * from room where organization_id = #{organization_id}")
    List<Room> selectByOrg(@Param("organization_id") Integer organization_id);

    @Update("update room set room_state = #{room_state} where room_id = #{room_id}")
    Integer updateStateById(@Param("room_id")Integer room_id,@Param("room_state")Integer room_state);
}
