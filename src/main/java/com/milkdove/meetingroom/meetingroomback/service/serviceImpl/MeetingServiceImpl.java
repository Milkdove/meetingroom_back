package com.milkdove.meetingroom.meetingroomback.service.serviceImpl;

import com.github.pagehelper.PageHelper;

import com.milkdove.meetingroom.meetingroomback.coverter.MeetingInfo;
import com.milkdove.meetingroom.meetingroomback.entites.Meeting;
import com.milkdove.meetingroom.meetingroomback.mapper.MeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingServiceImpl extends BaseService<Meeting> {

    @Autowired
    private MeetingMapper meetingMapper;

    @Autowired
    private UserServiceImpl userService;


    /**
     * 根据组织ID查询
     * 并且分页
     */
    public List<MeetingInfo> selectByOrg(Integer organization_id, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<Meeting> meetings = meetingMapper.selectByOrg(organization_id);
        List<MeetingInfo> list = new ArrayList<>();
        for(Meeting m:meetings){
            String user_name = userService.queryById(m.getUser_id()).getUser_name();
            String user_mobile = userService.queryById(m.getUser_id()).getUser_mobile();
            String admin_name = userService.queryById(m.getAdmin_id()).getUser_name();
            list.add(new MeetingInfo(m,user_name,admin_name,user_mobile));
        }
        return list;
    }
    /**
     * 根据UserID查询
     * 并且分页
     */
    public List<MeetingInfo> selectByuser_id(Integer user_id, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<Meeting> meetings = meetingMapper.selectByuser_id(user_id);
        List<MeetingInfo> list = new ArrayList<>();
        for(Meeting m:meetings){
            String user_name = userService.queryById(m.getUser_id()).getUser_name();
            String user_mobile = userService.queryById(m.getUser_id()).getUser_mobile();
            String admin_name = userService.queryById(m.getAdmin_id()).getUser_name();
            list.add(new MeetingInfo(m,user_name,admin_name,user_mobile));
        }
        return list;
    }
    /**
     * 根据adminID查询
     * 并且分页
     */
    public List<Meeting> selectByadmin_id(Integer admin_id, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        return meetingMapper.selectByadmin_id(admin_id);
    }

    /**
     * 修改状态
     * @param meeting_id
     * @param meeting_state
     * @return
     */
    public int updateStateById(Integer meeting_id,Integer meeting_state) {
        return meetingMapper.updateStateById(meeting_id,meeting_state);
    }

    public Meeting selectByName(String meet_name,Integer Organzation_id) {
        return meetingMapper.selectByName(meet_name,Organzation_id);
    }

}
