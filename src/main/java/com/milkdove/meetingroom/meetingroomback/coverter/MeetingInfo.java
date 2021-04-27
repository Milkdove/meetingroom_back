package com.milkdove.meetingroom.meetingroomback.coverter;

import com.milkdove.meetingroom.meetingroomback.entites.Meeting;
import com.milkdove.meetingroom.meetingroomback.entites.Users;


public class MeetingInfo extends Meeting {

    private String user_name;
    private String admin_name;
    private String user_mobile;

    public MeetingInfo() {
    }

    public MeetingInfo(String user_name, String admin_name, String user_mobile) {
        this.user_name = user_name;
        this.admin_name = admin_name;
        this.user_mobile = user_mobile;
    }

    public MeetingInfo(Meeting meeting,String user_name, String admin_name, String user_mobile) {
        super(meeting.getMeeting_name(),meeting.getMeeting_date(),meeting.getUser_id(),meeting.getRoom_id(),meeting.getAdmin_id(),
                meeting.getMeeting_state(),meeting.getMeeting_desc() ,meeting.getOrganization_id());
        this.user_name = user_name;
        this.admin_name = admin_name;
        this.user_mobile = user_mobile;
        setMeeting_id(meeting.getMeeting_id());
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }
}
