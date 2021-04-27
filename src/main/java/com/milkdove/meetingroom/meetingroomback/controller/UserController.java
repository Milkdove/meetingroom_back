package com.milkdove.meetingroom.meetingroomback.controller;

import com.milkdove.meetingroom.meetingroomback.entites.Users;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.UserServiceImpl;
import com.milkdove.meetingroom.meetingroomback.utils.JwtUtils;
import com.milkdove.meetingroom.meetingroomback.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
public class UserController {


    @Autowired
    private UserServiceImpl userService;


    public boolean checkToken( String token ){
        token = token.replace("[", "");
        token = token.replace("]", "");
        return JwtUtils.verify(token);
    }

    /**
     * 根据ID获取用户信息

     * @return
     */
    @RequestMapping(value ="/user/{user_id}", method = RequestMethod.GET)
    public Object userInfo(@PathVariable Integer user_id,@RequestHeader("Authorization") String token ) throws Exception {
        if(JwtUtils.verify(token)) {//token校验通过
            try {
                Users users = userService.queryById(user_id);
                return ResponseUtil.ok200(users);
            }catch (Exception e){
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }
    /**
     * 根据ID修改用户状态

     * @return
     */
    @RequestMapping(value ="/users/{user_id}/state/{user_state}", method = RequestMethod.PUT)
    public Object updateState(@PathVariable("user_id") Integer user_id,@PathVariable("user_state") Integer user_state,@RequestHeader("Authorization") String token){
        if(JwtUtils.verify(token)) {//token校验通过
            try {
                if(userService.updateStateById(user_id,user_state)==1){
                        return ResponseUtil.ok();
                    }
            } catch (Exception e) {
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }


    /**
     * 根据ID更新信息
     * @param users
     * @param token
     * @return
     */
    @RequestMapping (value ="/users/{user_id}",method = RequestMethod.PUT)
    public Object updateInfo(@RequestBody Users users, @RequestHeader("Authorization") String token){
        if(JwtUtils.verify(token)) {//token校验通过
            try {
                if (userService.updateNotNull(users) == 1) {
                    return ResponseUtil.ok();
                }

            } catch (Exception e) {
                return ResponseUtil.fail(500, "服务器异常！");
            }
        }
        return ResponseUtil.fail403();

    }

    /**
     * 加入组织
     * @return
     */
    @RequestMapping(value ="/joinOrg/{user_id}/{organization_id}", method = RequestMethod.PUT)
    public Object joinOrg(@PathVariable("user_id")Integer user_id ,@PathVariable("organization_id") Integer organization_id ,@RequestHeader("Authorization") String token) {
        if(JwtUtils.verify(token)) {//token校验通过
            try {
                userService.joinOrg(user_id, organization_id);
                userService.updateStateById(user_id, 0);
                return ResponseUtil.ok(200, "已提交申请,审核后生效！");

            } catch (Exception e) {
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }
    /**
     * 根据组织ID获取用户列表并分页
     * @return
     */
    @RequestMapping(value ="/users", method = RequestMethod.GET)
    public Object userInfoByOrf( int pagenum, int pagesize,@RequestHeader("Authorization") String token ) {
        try {
            if(checkToken(token)) {//token校验通过
                int a = (int) JwtUtils.getClaim(token).get("organization_id");
                List<Users> list = userService.queryUsersByOrgId(a, pagenum, pagesize);
                int total = userService.queryUsersByOrgId(a,0,0).size();
                return ResponseUtil.okList200(list,total);
                }
        }catch (Exception e){
            return ResponseUtil.fail500();
        }
        return ResponseUtil.fail403();
    }

    /**
     * 添加用户
     * @param token
     * @return
     */
    @RequestMapping(value ="/users", method = RequestMethod.POST)
    public Object addUser(@RequestBody Users users,@RequestHeader("Authorization") String token ) {
        try {
            if (checkToken(token)) {
                int organization_id = (int) JwtUtils.getClaim(token).get("organization_id");
                users.setOrganization_id(organization_id);
                userService.save(users);
                return ResponseUtil.ok(200,"添加用户成功！");
            }
        }catch (Exception e){
            return ResponseUtil.fail500();
        }
        return ResponseUtil.fail403();
    }

    /**
     * 删除用户
     * @param token
     * @return
     */
    @RequestMapping(value ="/users/{user_id}", method = RequestMethod.DELETE)
    public Object rmUsers( @PathVariable Integer user_id, @RequestHeader("Authorization") String token ) {
        try {
            if(JwtUtils.verify(token)) {//token校验通过
                userService.delete(user_id);
                return ResponseUtil.ok200();
            }
        }catch (Exception e){
            return ResponseUtil.fail500();
        }
        return ResponseUtil.fail403();
    }

}