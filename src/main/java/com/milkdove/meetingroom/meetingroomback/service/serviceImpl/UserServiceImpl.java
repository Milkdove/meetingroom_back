package com.milkdove.meetingroom.meetingroomback.service.serviceImpl;

import com.github.pagehelper.PageHelper;

import com.milkdove.meetingroom.meetingroomback.entites.Users;
import com.milkdove.meetingroom.meetingroomback.mapper.UserMapper;
import com.milkdove.meetingroom.meetingroomback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService<Users> implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Users> queryUsersByModel(Users user, int page, int rows) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(user.getUser_name())) {
            criteria.andLike("user_name", "%" + user.getUser_name() + "%");

        }
        if (StringUtil.isNotEmpty(user.getUser_mobile())) {
            criteria.andLike("user_mobile", "%" + user.getUser_mobile() + "%");
        }
        if (user.getOrganization_id() != null) {
            criteria.andEqualTo("organization_id", user.getOrganization_id());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }

    /**
     * 根据组织ID以及分页信息查询
     * @param organization_id
     * @param pagenum
     * @param pagesize
     * @return
     */
    @Override
    public List<Users> queryUsersByOrgId(Integer organization_id, int pagenum, int pagesize) {
        Users user = new Users();
        user.setOrganization_id(organization_id);
        return queryUsersByModel(user,pagenum,pagesize);
    }

    /**
     * 根据名称分页信息查询
     * @param user_name
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<Users> queryUsersByName(String user_name, int page, int rows) {
        Users user = new Users();
        user.setUser_name(user_name);
        return queryUsersByModel(user,page,rows);
    }

    /**
     * 登录
     * @param user_name
     * @param password
     * @return
     */
    public Users Login(String user_name,String password) {
        try {Users loginuser = userMapper.queryUserbyName(user_name);
            if(loginuser.getUser_password().equals(password)){
                return loginuser;
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 根据ID修改状态
     * @param user_id
     * @param user_state
     * @return
     * @throws Exception
     */
    public int updateStateById(Integer user_id,Integer user_state){
       return userMapper.updateStatebyId(user_id,user_state);

    }


    public Users queryById(Integer user_id) {
        return userMapper.queryUserbyId(user_id);
    }


    /**
     * 根据电话以及分页信息查询
     * @param user_mobile
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<Users> queryUsersByMobile(String user_mobile, int page, int rows)  {
        Users user = new Users();
        user.setUser_mobile(user_mobile);
        return queryUsersByModel(user,page,rows);
    }


    public void joinOrg(Integer user_id, Integer organization_id) {
        userMapper.joinOrg(user_id,organization_id);
    }
}
