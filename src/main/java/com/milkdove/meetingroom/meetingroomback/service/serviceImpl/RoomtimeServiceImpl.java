package com.milkdove.meetingroom.meetingroomback.service.serviceImpl;

import com.milkdove.meetingroom.meetingroomback.entites.Roomtime;
import com.milkdove.meetingroom.meetingroomback.mapper.RoomtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoomtimeServiceImpl extends BaseService<Roomtime> {

    @Autowired
   private RoomtimeMapper roomtimeMapper;

    public boolean timeCheck(Roomtime roomtime) {
        long starttime = roomtime.getMeeting_starttime().getTime();
        long endtime = roomtime.getMeeting_endtime().getTime();
        List <Roomtime> list = roomtimeMapper.selectByRoom(roomtime.getRoom_id());
        if(list==null){
            return true;
        }else {
            for (Roomtime rm:list){
                if((rm.getMeeting_starttime().getTime()<=starttime)&&(rm.getMeeting_endtime().getTime()>=starttime)){//新增会议的开始时间在其他会议时段中
                    return false;
                }
                if((rm.getMeeting_starttime().getTime()<=endtime)&&(rm.getMeeting_endtime().getTime()>=endtime)){//新增会议的结束时间在其他会议时段中
                    return false;
                }
                if((rm.getMeeting_starttime().getTime()>=starttime)&&(rm.getMeeting_starttime().getTime()<=endtime)){//已有会议的开始时间在新增会议时段中
                    return false;
                }
                if((rm.getMeeting_endtime().getTime()>=starttime)&&(rm.getMeeting_endtime().getTime()<=endtime)){//已有会议的结束时间在新增会议时段中
                    return false;
                }
            }
            return true;
        }

    }

    @Override
    public int save(Roomtime roomtime) {
        return super.save(roomtime);
    }

    public List<Roomtime> selectAllByRoom(Integer room_id) {
        return roomtimeMapper.selectByRoom(room_id);
    }
}
