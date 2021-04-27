package com.milkdove.meetingroom.meetingroomback.entites;

import com.milkdove.meetingroom.meetingroomback.coverter.MeetingVo;

import javax.persistence.Id;

/**
 * meeting会议申请表
 */
public class Meeting {
    @Id
    private Integer meeting_id;
    private String meeting_name;
    private String meeting_date;
    private Integer user_id;
    private Integer room_id;
    private Integer admin_id;
    private Integer meeting_state;
    private String meeting_desc;
    private Integer organization_id;


    public Meeting() {
    }
    public Meeting(MeetingVo meetingVo) {
        this.meeting_name=meetingVo.getMeeting_name();
        this.meeting_date=meetingVo.toString();
        this.meeting_state=0;
        this.admin_id=meetingVo.getAdmin_id();
        this.user_id=meetingVo.getUser_id();
        this.room_id=meetingVo.getRoom_id();
        this.organization_id=meetingVo.getOrganization_id();
        this.meeting_desc=meetingVo.getMeeting_desc();
    }

    public Meeting(String meeting_name, String meeting_date, Integer user_id, Integer room_id, Integer admin_id, Integer meeting_state, String meeting_desc,Integer organization_id) {
        this.meeting_name = meeting_name;
        this.meeting_date = meeting_date;
        this.user_id = user_id;
        this.room_id = room_id;
        this.admin_id = admin_id;
        this.meeting_state = meeting_state;
        this.meeting_desc = meeting_desc;
        this.organization_id = organization_id;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }

    public String getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(String meeting_date) {
        this.meeting_date = meeting_date;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public Integer getMeeting_state() {
        return meeting_state;
    }

    public void setMeeting_state(Integer meeting_state) {
        this.meeting_state = meeting_state;
    }

    public String getMeeting_desc() {
        return meeting_desc;
    }

    public void setMeeting_desc(String meeting_desc) {
        this.meeting_desc = meeting_desc;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meeting_id=" + meeting_id +
                ", meeting_name='" + meeting_name + '\'' +
                ", meeting_date='" + meeting_date + '\'' +
                ", user_id=" + user_id +
                ", room_id=" + room_id +
                ", admin_id=" + admin_id +
                ", meeting_state=" + meeting_state +
                ", meeting_desc='" + meeting_desc + '\'' +
                ", organization_id=" + organization_id +
                '}';
    }
}
