package com.milkdove.meetingroom.meetingroomback.coverter;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingVo {

    private String meeting_name;
    private ArrayList<Date> meeting_date;
    private Integer user_id;
    private Integer room_id;
    private Integer admin_id;
    private Integer meeting_size;
    private String meeting_desc;
    private Integer organization_id;
    private Integer meeting_state;
    private String room_type;

    public MeetingVo() {
    }

    public MeetingVo(String meeting_name, ArrayList<Date> meeting_date, Integer user_id, Integer room_id, Integer admin_id, Integer meeting_size, String meeting_desc, Integer organization_id, Integer meeting_state, String room_type) {
        this.meeting_name = meeting_name;
        this.meeting_date = meeting_date;
        this.user_id = user_id;
        this.room_id = room_id;
        this.admin_id = admin_id;
        this.meeting_size = meeting_size;
        this.meeting_desc = meeting_desc;
        this.organization_id = organization_id;
        this.meeting_state = 0;
        this.room_type = room_type;
    }

    public String getMeeting_name() {
        return meeting_name;
    }

    public void setMeeting_name(String meeting_name) {
        this.meeting_name = meeting_name;
    }

    public ArrayList<Date> getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(ArrayList<Date> meeting_date) {
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

    public Integer getMeeting_size() {
        return meeting_size;
    }

    public void setMeeting_size(Integer meeting_size) {
        this.meeting_size = meeting_size;
    }

    public String getMeeting_desc() {
        return meeting_desc;
    }

    public void setMeeting_desc(String meeting_desc) {
        this.meeting_desc = meeting_desc;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public Integer getMeeting_state() {
        return meeting_state;
    }

    public void setMeeting_state(Integer meeting_state) {
        this.meeting_state = meeting_state;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    @Override
    public String toString() {
        SimpleDateFormat matter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        return  matter.format(meeting_date.get(0)) +
                "至" + matter.format(meeting_date.get(1)) ;
    }
}
