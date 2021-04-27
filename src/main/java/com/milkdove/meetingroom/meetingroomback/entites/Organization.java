package com.milkdove.meetingroom.meetingroomback.entites;

import javax.persistence.Id;


public class Organization {

    @Id
    private Integer organization_id;
    private String organization_name;
    private Integer admin_id;

    public Organization() {
    }

    public Organization( String organization_name, Integer admin_id) {
        this.organization_name = organization_name;
        this.admin_id = admin_id;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organization_id=" + organization_id +
                ", organization_name='" + organization_name + '\'' +
                ", admin_id=" + admin_id +
                '}';
    }
}
