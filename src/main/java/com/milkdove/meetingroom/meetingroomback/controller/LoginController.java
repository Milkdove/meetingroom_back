package com.milkdove.meetingroom.meetingroomback.controller;

import com.milkdove.meetingroom.meetingroomback.entites.Users;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.UserServiceImpl;
import com.milkdove.meetingroom.meetingroomback.utils.JwtUtils;
import com.milkdove.meetingroom.meetingroomback.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * @return
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public Object login(@RequestBody Users users) {
        users= userService.Login(users.getUser_name(),users.getUser_password());//验证密码
        if(users!=null){
            if(users.getUser_state()==0){//检测账号状态
                return ResponseUtil.fail(401,"该账号未通过审核！");
            }
            Map<String,Object> map = new HashMap<>();
            map.put("user_id",users.getUser_id());
            map.put("organization_id",users.getOrganization_id());
            String token = JwtUtils.generate(map); //正确生成Token
            return ResponseUtil.okAndToken(users,token);
        }
        return ResponseUtil.fail(400,"用户名或密码错误！");
    }
}
