package com.milkdove.meetingroom.meetingroomback.entites;

import javax.persistence.Id;

public class Room {
    @Id
    private Integer room_id;
    private String room_name;
    private String room_no;
    private String room_type;
    private Integer room_size;
    private Integer room_state ;
    private Integer organization_id;
    private Integer admin_id;
    private String  room_desc;

    public Room() {
    }

    public Room(Integer room_id, String room_name, String room_no, String room_type, Integer room_size, Integer room_state, Integer organization_id, Integer admin_id, String room_desc) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.room_no = room_no;
        this.room_type = room_type;
        this.room_size = room_size;
        this.room_state = room_state;
        this.organization_id = organization_id;
        this.admin_id = admin_id;
        this.room_desc = room_desc;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Integer getRoom_size() {
        return room_size;
    }

    public void setRoom_size(Integer room_size) {
        this.room_size = room_size;
    }

    public Integer getRoom_state() {
        return room_state;
    }

    public void setRoom_state(Integer room_state) {
        this.room_state = room_state;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getRoom_desc() {
        return room_desc;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", room_name='" + room_name + '\'' +
                ", room_no='" + room_no + '\'' +
                ", room_type='" + room_type + '\'' +
                ", room_size=" + room_size +
                ", room_state=" + room_state +
                ", organization_id=" + organization_id +
                ", admin_id=" + admin_id +
                ", room_desc='" + room_desc + '\'' +
                '}';
    }

    public void setRoom_desc(String room_desc) {
        this.room_desc = room_desc;
    }
}
