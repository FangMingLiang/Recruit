package com.mingliang.recruit.service;


import com.mingliang.recruit.model.ForbidUser;

import java.util.List;

public interface ForbidUserService {
    public void AddForbidUser(ForbidUser forbidUser);
    public List<ForbidUser> FindForbidUsersList();
}
