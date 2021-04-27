package com.milkdove.meetingroom.meetingroomback.service;


import com.milkdove.meetingroom.meetingroomback.entites.Organization;

import java.util.List;

public interface OrganizationService extends IService<Organization> {

    /**
     * 根据条件分页查询
     *
     * @param organization
     * @param page
     * @param rows
     * @return
     */
    List<Organization> selectByOrganization(Organization organization, int page, int rows);
}
