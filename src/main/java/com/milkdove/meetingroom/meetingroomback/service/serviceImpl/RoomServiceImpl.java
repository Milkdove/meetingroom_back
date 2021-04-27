package com.milkdove.meetingroom.meetingroomback.service.serviceImpl;

import com.github.pagehelper.PageHelper;

import com.milkdove.meetingroom.meetingroomback.entites.Room;
import com.milkdove.meetingroom.meetingroomback.mapper.RoomMapper;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl extends BaseService<Room> {

    /**
     * 根据组织ID返回会议室
     * 并且分页
     */
    @Autowired
    private RoomMapper roomMapper;
    public List<Room> selectByOrg(Integer organization_id, int page, int rows) {
        PageHelper.startPage(page, rows);
        return roomMapper.selectByOrg(organization_id);
    }


    /**
     * 修改状态
     * @param room_id
     * @return
     */
    public int updateStateById (Integer room_id,Integer room_state ){
        return roomMapper.updateStateById(room_id,room_state);
    }
}
