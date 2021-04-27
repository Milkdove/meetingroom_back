package com.milkdove.meetingroom.meetingroomback.entites;


import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

/**
 * user类 用户信息
 */
@JsonRootName("Users")
public class Users {

    @Id
    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_mobile;
    private String user_email;
    private Integer user_state;
    private Integer user_role;
    private Integer organization_id;
    private String organization_name;



    public Users() {
    }

    public Users( String user_name, String user_password, String user_mobile, String user_email, Integer user_state, Integer user_role, Integer organization_id, String organization_name) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_mobile = user_mobile;
        this.user_email = user_email;
        this.user_state = user_state;
        this.user_role = user_role;
        this.organization_id = organization_id;
        this.organization_name = organization_name;
    }

    public Integer getUser_state() {
        return user_state;
    }
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_state(Integer user_state) {
        this.user_state = user_state;
    }

    public Integer getUser_role() {
        return user_role;
    }

    public void setUser_role(Integer user_role) {
        this.user_role = user_role;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_mobile='" + user_mobile + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_state=" + user_state +
                ", user_role=" + user_role +
                ", organization_id=" + organization_id +
                ", organization_name='" + organization_name + '\'' +
                '}';
    }
}
