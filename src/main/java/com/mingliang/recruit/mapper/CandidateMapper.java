package com.mingliang.recruit.mapper;

import com.mingliang.recruit.model.Candidate;

import java.util.List;

public interface CandidateMapper {
    int deleteByPrimaryKey(String candidateId);

    int insert(Candidate record);

    int insertSelective(Candidate record);

    Candidate selectByPrimaryKey(String candidateId);

    int updateByPrimaryKeySelective(Candidate record);

    int updateByPrimaryKey(Candidate record);

    List<Candidate> FindAllCandidates(String sign);//查找符合标记的全部的求职者list
}