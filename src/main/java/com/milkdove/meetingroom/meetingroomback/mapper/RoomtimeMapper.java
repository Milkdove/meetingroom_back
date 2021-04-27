package com.milkdove.meetingroom.meetingroomback.mapper;

import com.milkdove.meetingroom.meetingroomback.entites.Roomtime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoomtimeMapper extends Mapper<Roomtime> {

    @Select("select * from roomtime where room_id = #{room_id}")
    List<Roomtime> selectByRoom(@Param("room_id") Integer room_id);

}
