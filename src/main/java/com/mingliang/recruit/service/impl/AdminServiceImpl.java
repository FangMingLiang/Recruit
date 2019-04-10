package com.mingliang.recruit.service.impl;

import com.mingliang.recruit.mapper.AdminMapper;
import com.mingliang.recruit.model.Admin;
import com.mingliang.recruit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public Admin AdminLoginResult(String adminid){
        return adminMapper.selectByPrimaryKey(adminid);
    }
}
