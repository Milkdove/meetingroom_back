package com.milkdove.meetingroom.meetingroomback.utils;


public class Meta {
    Integer status;
    String message;

    public Meta() {
    }

    public Meta(Integer status,String message) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
