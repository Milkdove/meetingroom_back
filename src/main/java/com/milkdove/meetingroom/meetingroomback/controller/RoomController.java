package com.milkdove.meetingroom.meetingroomback.controller;

import com.milkdove.meetingroom.meetingroomback.entites.Room;
import com.milkdove.meetingroom.meetingroomback.entites.Roomtime;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.RoomServiceImpl;
import com.milkdove.meetingroom.meetingroomback.service.serviceImpl.RoomtimeServiceImpl;
import com.milkdove.meetingroom.meetingroomback.utils.JwtUtils;
import com.milkdove.meetingroom.meetingroomback.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class RoomController {

    @Autowired
    private RoomServiceImpl roomService;
    @Autowired
    private RoomtimeServiceImpl roomtimeService;


    /**
     * 根据组织_id查询会议室
     * @param pagenum
     * @param pagesize
     * @param token
     * @return
     */
    @RequestMapping(value ="/rooms", method = RequestMethod.GET)
    public Object queryAllRooms( int pagenum, int pagesize,@RequestHeader("Authorization") String token ) {

        if(JwtUtils.verify(token)){//token校验通过
            try {
                int organization_id = (int) JwtUtils.getClaim(token).get("organization_id");
                List<Room> list = roomService.selectByOrg(organization_id, pagenum, pagesize);
                int total = roomService.selectByOrg(organization_id, 0, 0).size();
                return ResponseUtil.okList200(list, total);
             } catch (Exception e) {
                return ResponseUtil.fail500();
            }
         }
        return ResponseUtil.fail403();
    }

    /**
     * 根据会议室ID查询
     * @param token
     * @return
     */
    @RequestMapping(value ="/rooms/{room_id}", method = RequestMethod.GET)
    public Object queryRoomsById( @PathVariable Integer room_id ,@RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)){//token校验通过
            try {
                Room room = roomService.selectByKey(room_id);
                List<Roomtime> list = roomtimeService.selectAllByRoom(room_id);
                return ResponseUtil.okListAndData200(list,room);
            } catch (Exception e) {
            return ResponseUtil.fail500();
        }
    }
        return ResponseUtil.fail403();
    }

    /**
     * 添加会议室
     * @param token
     * @return
     */
    @RequestMapping(value ="/rooms", method = RequestMethod.POST)
    public Object addRooms(@RequestBody Room room, HttpServletRequest httpServletRequest, @RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)) {//token校验通过
            try {
                Object a = httpServletRequest.getAttribute("room_state");
                int organization_id = (int) JwtUtils.getClaim(token).get("organization_id");
                int admin_id = (int) JwtUtils.getClaim(token).get("user_id");
                room.setOrganization_id(organization_id);
                room.setAdmin_id(admin_id);
                roomService.save(room);
                return ResponseUtil.ok200();
            } catch (Exception e) {
                if (e.getMessage().contains("Duplicate")) {
                    return ResponseUtil.fail(500, "该会议名称已被使用！");
                }
                System.out.println(e);
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }


    /**
     * 删除会议室
     * @param token
     * @return
     */
    @RequestMapping(value ="/rooms/{room_id}", method = RequestMethod.DELETE)
    public Object rmRooms( @PathVariable Integer room_id, @RequestHeader("Authorization") String token ) {
        if(JwtUtils.verify(token)){//token校验通过
            try {
                roomService.delete(room_id);
                return ResponseUtil.ok200();
            } catch (Exception e){
            return ResponseUtil.fail500();
        }
    }
        return ResponseUtil.fail403();
    }

    /**
     * 根据ID修改状态

     * @return
     */
    @RequestMapping(value ="/rooms/{room_id}/state/{room_state}", method = RequestMethod.PUT)
    public Object roomState(@PathVariable("room_id") Integer room_id,@PathVariable("room_state") Integer room_state,@RequestHeader("Authorization") String token){
        if(JwtUtils.verify(token)){//token校验通过
            try {
                if(roomService.updateStateById(room_id,room_state)==1){
                    return ResponseUtil.ok();
                }
            } catch (Exception e) {
                System.out.println(e);
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }


    /**
     * 根据ID修改信息
     * @return
     */
    @RequestMapping(value ="/rooms/{room_id}", method = RequestMethod.PUT)
    public Object updateRoomInfo(@PathVariable("room_id") Integer room_id,@RequestBody Room room,@RequestHeader("Authorization") String token){
        if(JwtUtils.verify(token)){//token校验通过
            try {
                if(roomService.updateNotNull(room)==1){
                    return ResponseUtil.ok();
                }
            } catch (Exception e) {
                return ResponseUtil.fail500();
            }
        }
        return ResponseUtil.fail403();
    }


}