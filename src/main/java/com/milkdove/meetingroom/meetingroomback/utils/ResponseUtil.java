package com.milkdove.meetingroom.meetingroomback.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 响应格式
 *  {
 *      data:{
 *
 *      },
 *      meta:{
 *          status:
 *          message:
 *      }
 *  }
 *
 *  200 成功
 *
 *
 */

public class ResponseUtil<T> {


    public static Object ok() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("meta", new Meta(200,"操作成功"));
        return obj;
    }
    public static Object ok(Integer status ,String message) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("meta", new Meta(status,message));
        return obj;
    }
    public static Object fail() {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("meta", new Meta(400,"失败了，请重试"));
        return obj;
    }

    public static Object fail(Integer status,String message) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("meta", new Meta(status,message));
        return obj;
    }


    public static Object ok200(Object data) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("meta", new Meta(200,"操作成功"));
        obj.put("data", data);
        return obj;
    }

    public static Object okList200(List list,int total) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);
        data.put("total", total);
        return ok200(data);
    }
    public static Object okListAndData200(List list,Object obj) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);
        data.put("data", obj);
        data.put("meta", new Meta(200,"成功"));
        return data;
    }



    public static Object fail400() {
        return fail(400,"请求有误！");
    }




    public static Object okAndToken(Object data,String token) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("meta", new Meta(200,"操作成功"));
        obj.put("data", data);
        obj.put("token", token);
        return obj;
    }


    public static Object ok200() {
        return ok(200,"操作完成！");
    }
    public static Object fail403() {
        return fail(403,"权限不通过！");
    }
    public static Object fail500() {
        return fail(500,"服务器出现异常");
    }
}
