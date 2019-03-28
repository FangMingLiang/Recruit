package com.mingliang.recruit.mapper;

import com.mingliang.recruit.model.Resume;

public interface ResumeMapper {
    int deleteByPrimaryKey(String candidateid);

    int insert(Resume record);

    int insertSelective(Resume record);

    Resume selectByPrimaryKey(String candidateid);

    int updateByPrimaryKeySelective(Resume record);

    int updateByPrimaryKeyWithBLOBs(Resume record);

    int updateByPrimaryKey(Resume record);
}