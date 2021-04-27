package com.milkdove.meetingroom.meetingroomback;

import com.milkdove.meetingroom.meetingroomback.entites.Meeting;
import com.milkdove.meetingroom.meetingroomback.entites.Organization;
import com.milkdove.meetingroom.meetingroomback.entites.Room;
import com.milkdove.meetingroom.meetingroomback.entites.Users;
import com.milkdove.meetingroom.meetingroomback.service.UserService;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.MeetingServiceImpl;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.OrganizationServiceImpl;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.RoomServiceImpl;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.UserServiceImpl;
import com.milkdove.meetingroom.meetingroomback.utils.JwtUtils;

import com.milkdove.meetingroom.meetingroomback.utils.ResponseUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tk.mybatis.mapper.entity.Example;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DaoTest {


    @Test
    public void userDao(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userServiceImpl",UserServiceImpl.class);
        String query="1";

//        for (Users a: list
//                ) {
//            System.out.println(a);
//        }
    }


    @Test
    public void jwtTest(){

        Map<String,Object> map = new HashMap<>();
        map.put("user_id","123");
        map.put("organization_id","1");
        String token = JwtUtils.generate(map);
        System.out.println(token);
        map = JwtUtils.getClaim(token);
        System.out.println(map);

        System.out.println(JwtUtils.isSigned(token));
        System.out.println(JwtUtils.verify(token));

    }
    @Test
    public void ResTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userService = context.getBean("userServiceImpl",UserServiceImpl.class);
        List<Users> ulist=userService.queryUsersByOrgId(1,0,0);
        Users user = userService.selectByKey(1);
        Object res = ResponseUtil.ok200(user);
        Object res2 = ResponseUtil.okList200(ulist,10);
        System.out.println(res);
        System.out.println(res2);
    }

    @Test
    public void testLogin() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userService = context.getBean("userServiceImpl",UserServiceImpl.class);
        Users users = new Users();
        List<Users> ilist ;
//        users =  userService.Login("yTyhs89h1W1","123456");
//        int a = userService.updateStateById(200,true);
//        userService.queryUsersByModel(users,2,3);
//        users.setUser_name("123");
//        users = userService.getMapper().selectByPrimaryKey(1);
        ilist = userService.queryUsersByName("yTyhs89h1W",0,0);
        for(Users a :ilist){
            System.out.println(a);
        }
        System.out.println(users);
    }

    @Test
    public void OrgDao(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrganizationServiceImpl OrganizationServiceImpl = context.getBean("OrganizationServiceImpl",OrganizationServiceImpl.class);
        Organization newo = new Organization("adminORG",1);
        Organization o = OrganizationServiceImpl.selectByOrganization(newo,0,0).get(0);
//        OrganizationServiceImpl.getMapper().insert(newo);
        System.out.println(o);
        System.out.println();
    }

//    @Test
//    public void roomDao(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        RoomServiceImpl roomServiceImpl = context.getBean("RoomServiceImpl",RoomServiceImpl.class);
//        Room room = new Room("122354","13","多媒体",50,true,20,1);
//        int a= roomServiceImpl.delete(4);
//        List<Room> rlist = roomServiceImpl.selectByOrg(20,1,2);
//        for (Room r: rlist) {
//            System.out.println(r);
//        }
//        System.out.println(a);
//
//
//    }

//    @Test
//    public void meetingDao(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        MeetingServiceImpl meetingServiceImpl = context.getBean("MeetingServiceImpl",MeetingServiceImpl.class);
////        int a= meetingServiceImpl.selectAll();
//        List<Meeting> mlist = meetingServiceImpl.selectAll();
//        Meeting meeting = new Meeting("552135",null,null,null,null,true,null,13);
//        meeting.setMeeting_id(127);
//        System.out.println(meetingServiceImpl.updateStateById(12,true));
////        System.out.println(meetingServiceImpl.deleteById(124));
//        System.out.println(meetingServiceImpl.updateNotNull(meeting));
//        mlist  = meetingServiceImpl.selectByadmin_id(1,0,0);
//        for (Meeting m: mlist) {
//            System.out.println(m);
//        }
////        System.out.println(a);
//
//    }


    @Test
    public void queryOrg() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrganizationServiceImpl organizationServicer  = context.getBean("organizationServiceImpl",OrganizationServiceImpl.class);
        Example example = new Example(Organization.class);
        example.createCriteria().andGreaterThan("admin_id",1);
        List<Organization> list ;
//        list = organizationServicer.selectByExample(example);
//        for(int i=0;i<20;i++){
//            String organization_name=getRandomString(10);
//            Integer admin_id=1;
//            Organization organization = new Organization(organization_name,admin_id);
////            Users user = new Users(user_name,user_password,user_mobile,user_email,user_state,user_role,organization_id,organization_name);
//            organizationServicer.save(organization);
//            System.out.println("添加"+i);
//        }
//        PageHelper.startPage(2,9);
//        list =  organizationServicer.getMapper().selectAll();
//
//        for (Organization a: list
//             ) {
//            System.out.println(a);
//        }
    }
}
