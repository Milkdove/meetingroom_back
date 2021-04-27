package com.milkdove.meetingroom.meetingroomback.service.serviceImpl;

import com.github.pagehelper.PageHelper;

import com.milkdove.meetingroom.meetingroomback.entites.Organization;
import com.milkdove.meetingroom.meetingroomback.mapper.OrganizationMapper;
import com.milkdove.meetingroom.meetingroomback.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
@Service
public class OrganizationServiceImpl extends BaseService<Organization> implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;
    @Override
    public List<Organization> selectByOrganization(Organization organization, int page, int rows) {
        Example example = new Example(Organization.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(organization.getOrganization_name())) {
            criteria.andLike("organization_name", "%" + organization.getOrganization_name() + "%");
        }
        if (organization.getOrganization_id()!=null) {
            criteria.andLike("organization_id", "%" + organization.getOrganization_id() + "%");
        }
        if (organization.getAdmin_id() != null) {
            criteria.andEqualTo("admin_id", organization.getAdmin_id());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }


    public Organization insert(Organization organization) {
       if(super.save(organization)==1){
           return organizationMapper.queryUserbyName(organization.getOrganization_name());
       }
       return null;
    }
}
