package com.mingliang.recruit.service.impl;

import com.mingliang.recruit.mapper.ForbidUserMapper;
import com.mingliang.recruit.model.ForbidUser;
import com.mingliang.recruit.service.ForbidUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ForbidUserServiceImpl implements ForbidUserService {
    @Autowired
    private ForbidUserMapper forbidUserMapper;
    @Override
    public void AddForbidUser(ForbidUser forbidUser) {
        forbidUserMapper.insert(forbidUser);
    }

    @Override
    public List<ForbidUser> FindForbidUsersList() {
        return null;
    }
}
