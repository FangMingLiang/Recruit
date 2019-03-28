package com.mingliang.recruit.mapper;

import com.mingliang.recruit.model.Candidate;

public interface CandidateMapper {
    int deleteByPrimaryKey(String candidateId);

    int insert(Candidate record);

    int insertSelective(Candidate record);

    Candidate selectByPrimaryKey(String candidateId);

    int updateByPrimaryKeySelective(Candidate record);

    int updateByPrimaryKey(Candidate record);
}