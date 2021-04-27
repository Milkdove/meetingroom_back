package com.milkdove.meetingroom.meetingroomback.controller;

import com.milkdove.meetingroom.meetingroomback.entites.Organization;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.OrganizationServiceImpl;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.UserServiceImpl;
import com.milkdove.meetingroom.meetingroomback.utils.JwtUtils;
import com.milkdove.meetingroom.meetingroomback.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class OrgController {

    @Autowired
    private OrganizationServiceImpl organizationService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 根据ID获取组织信息

     * @return
     */
    @RequestMapping(value ="/organizations/{organization_id}", method = RequestMethod.GET)
    public Object orgInfo(@PathVariable Integer organization_id,@RequestHeader("Authorization") String token){
        if(JwtUtils.verify(token)) {//token校验通过
            try {
                Organization organization = organizationService.selectByKey(organization_id);
                return ResponseUtil.ok200(organization);
            } catch (Exception e) {
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }

    /**
     * 解散组织
     * @param token
     * @return
     */
    @RequestMapping(value ="/organizations/{organization_id}", method = RequestMethod.DELETE)
    public Object rmOrgs( @PathVariable Integer organization_id, @RequestHeader("Authorization") String token ) {
        try {
            if(JwtUtils.verify(token)) {//token校验通过
                organizationService.delete(organization_id);
                return ResponseUtil.ok200();
            }
        }catch (Exception e){
            return ResponseUtil.fail500();
        }
        return ResponseUtil.fail403();
    }

    /**
     * 创建组织
     * @param token
     * @return
     */
    @RequestMapping(value ="/organizations", method = RequestMethod.POST)
    public Object addOrg( @RequestBody String organization_name, @RequestHeader("Authorization") String token ) {
        try {
            if(JwtUtils.verify(token)) {//token校验通过
                Integer admin_id = (Integer) JwtUtils.getClaim(token).get("user_id");
                Organization organization = new Organization();
                organization.setOrganization_name(organization_name);
                organization.setAdmin_id(admin_id);
                organization = organizationService.insert(organization);
                userService.joinOrg(admin_id,organization.getOrganization_id());
                return ResponseUtil.ok200(organization);
            }
        }catch (Exception e){
            return ResponseUtil.fail500();
        }
        return ResponseUtil.fail403();
    }




}
