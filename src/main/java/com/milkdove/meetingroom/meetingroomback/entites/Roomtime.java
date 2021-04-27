package com.milkdove.meetingroom.meetingroomback.entites;

import javax.persistence.Id;
import java.util.Date;

/**
 * 存放会议室被占用的时间
 */
public class Roomtime {
    private Integer room_id;
    @Id
    private Integer meeting_id;
    private Date meeting_starttime;
    private Date meeting_endtime;
    private Integer organization_id;

    public Roomtime() {
    }

    public Roomtime(Integer room_id, Integer meeting_id, Date meeting_starttime, Date meeting_endtime, Integer organization_id) {
        this.room_id = room_id;
        this.meeting_id = meeting_id;
        this.meeting_starttime = meeting_starttime;
        this.meeting_endtime = meeting_endtime;
        this.organization_id = organization_id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public Date getMeeting_starttime() {
        return meeting_starttime;
    }

    public void setMeeting_starttime(Date meeting_starttime) {
        this.meeting_starttime = meeting_starttime;
    }

    public Date getMeeting_endtime() {
        return meeting_endtime;
    }

    public void setMeeting_endtime(Date meeting_endtime) {
        this.meeting_endtime = meeting_endtime;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    @Override
    public String toString() {
        return "meeting_starttime=" + meeting_starttime +
                ", meeting_endtime=" + meeting_endtime ;
    }
}
