package com.milkdove.meetingroom.meetingroomback.controller;

import com.milkdove.meetingroom.meetingroomback.coverter.MeetingInfo;
import com.milkdove.meetingroom.meetingroomback.coverter.MeetingVo;
import com.milkdove.meetingroom.meetingroomback.entites.Meeting;
import com.milkdove.meetingroom.meetingroomback.entites.Roomtime;
import com.milkdove.meetingroom.meetingroomback.entites.Users;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.MeetingServiceImpl;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.RoomServiceImpl;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.RoomtimeServiceImpl;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.UserServiceImpl;
import com.milkdove.meetingroom.meetingroomback.utils.JwtUtils;
import com.milkdove.meetingroom.meetingroomback.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class MeetingController {

    @Autowired
    private MeetingServiceImpl meetingService;
    @Autowired
    private RoomtimeServiceImpl timeService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 根据user_id查询会议
     * @param pagenum
     * @param pagesize
     * @param token
     * @return
     */
    @RequestMapping(value ="/meetings", method = RequestMethod.GET)
    public Object queryMyMeeting( int pagenum, int pagesize,@RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)) {  //token校验通过
            try {
                int user_id = (int) JwtUtils.getClaim(token).get("user_id");
                List<MeetingInfo> list = meetingService.selectByuser_id(user_id, pagenum, pagesize);
                int total = meetingService.selectByuser_id(user_id, 0, 0).size();
                return ResponseUtil.okList200(list, total);
            } catch (Exception e) {
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }


    /**
     * 根据组织_id查询会议
     * @param pagenum
     * @param pagesize
     * @param token
     * @return
     */
    @RequestMapping(value ="/allmeetings", method = RequestMethod.GET)
    public Object queryOrgMeeting( int pagenum, int pagesize,@RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)) {  //token校验通过
            try {
                int organization_id = (int) JwtUtils.getClaim(token).get("organization_id");
                List<MeetingInfo> list = meetingService.selectByOrg(organization_id, pagenum, pagesize);
                int total = meetingService.selectByOrg(organization_id, 0, 0).size();
                return ResponseUtil.okList200(list, total);
            } catch (Exception e) {
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }

    /**
     * 根据会议_id查询会议
     * @param meeting_id
     * @param token
     * @return
     */
    @RequestMapping(value ="/meeting/{meeting_id}", method = RequestMethod.GET)
    public Object queryOrgMeeting( @PathVariable("meeting_id") Integer meeting_id,@RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)) {  //token校验通过
            try {
                Meeting meeting = meetingService.selectByKey(meeting_id);
                Users users = userService.selectByKey(meeting.getUser_id());
                Users admin = userService.selectByKey(meeting.getAdmin_id());
                MeetingInfo meetingInfo = new MeetingInfo(meeting, users.getUser_name(), admin.getUser_name(), users.getUser_mobile());
                return ResponseUtil.ok200(meetingInfo);

            } catch (Exception e) {
                return ResponseUtil.fail(500, "该申请状态异常!请联系管理员");
            }
        }
        return ResponseUtil.fail403();
    }

    /**
     * 预定会议室
     * @param token
     * @return
     */
    @RequestMapping(value ="/meetings", method = RequestMethod.POST)
    public Object bookMeeting(@RequestBody MeetingVo meetingVo, @RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)) {  //token校验通过
            try {
                if (meetingVo.getMeeting_date().get(0).getTime() < new Date().getTime()) {//验证时间是否合法
                    return ResponseUtil.fail(400, "时间选择有误!");
                }
                Integer user_id = (Integer) JwtUtils.getClaim(token).get("user_id");
                Integer organization_id = (Integer) JwtUtils.getClaim(token).get("organization_id");
                meetingVo.setUser_id(user_id);
                Roomtime rm = new Roomtime(meetingVo.getRoom_id(), null, meetingVo.getMeeting_date().get(0), meetingVo.getMeeting_date().get(1), organization_id);
                if (!timeService.timeCheck(rm)) {//验证时间是否有效
                    return ResponseUtil.fail(400, "该时段已被预定！");
                }
                Meeting meeting = new Meeting(meetingVo);
                meetingService.save(meeting);
                Integer meeting_id = meetingService.selectByName(meeting.getMeeting_name(), meeting.getOrganization_id()).getMeeting_id();
                rm.setMeeting_id(meeting_id);
                timeService.save(rm);
                return ResponseUtil.ok200();
            } catch (Exception e) {
                if (e.getMessage().contains("Duplicate")) {
                    return ResponseUtil.fail(500, "该会议名称已被使用！");
                }
                return ResponseUtil.fail(500, "服务器错误！");
            }
        }
        return ResponseUtil.fail403();
    }

    /**
     * 删除会议申请
     * @param token
     * @return
     */
    @RequestMapping(value ="/meetings/{meeting_id}", method = RequestMethod.DELETE)
    public Object rmMeeting( @PathVariable Integer meeting_id, @RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)) {  //token校验通过
            try {
                meetingService.delete(meeting_id);
                return ResponseUtil.ok200();
            } catch (Exception e) {
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }

    /**
     * 审批会议申请
     * @param token
     * @return
     */
    @RequestMapping(value ="/meeting/{meeting_id}", method = RequestMethod.PUT)
    public Object agreeMeeting( @PathVariable Integer meeting_id, @RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)) {  //token校验通过
            try {
                meetingService.updateStateById(meeting_id,1);
                return ResponseUtil.ok200();
            } catch (Exception e) {
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }


}
