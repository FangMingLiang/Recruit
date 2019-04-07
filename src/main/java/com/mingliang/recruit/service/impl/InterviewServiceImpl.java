package com.mingliang.recruit.service.impl;

import com.mingliang.recruit.mapper.InterviewMapper;
import com.mingliang.recruit.model.Interview;
import com.mingliang.recruit.model.Position;
import com.mingliang.recruit.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    private InterviewMapper interviewMapper;
    @Override
    public void AddInterview(Interview interview) {
        interviewMapper.insert(interview);
    }

    @Override
    public List<Position> FindDeliverListBySign(String candidateid) {
        return interviewMapper.FindDeliverListBySign(candidateid);
    }

}
