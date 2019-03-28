package com.mingliang.recruit.service.impl;


import com.mingliang.recruit.mapper.CandidateMapper;
import com.mingliang.recruit.model.Candidate;
import com.mingliang.recruit.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateMapper candidateMapper;


    @Override
    public Candidate LoginResult(String CandidateId){
        return candidateMapper.selectByPrimaryKey(CandidateId);
    }

    @Override
    public void Add(Candidate candidate){
        candidateMapper.insert(candidate);
    }
}
