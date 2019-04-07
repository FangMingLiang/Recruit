package com.mingliang.recruit.service;

import com.mingliang.recruit.model.Interview;
import com.mingliang.recruit.model.Position;

import java.util.List;

public interface InterviewService {
    public void AddInterview(Interview interview);
    public List<Position> FindDeliverListBySign(String candidateid);
}
