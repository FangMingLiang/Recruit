package com.mingliang.recruit.service;

import com.mingliang.recruit.model.Candidate;

public interface CandidateService {
    public Candidate LoginResult(String CandidateId);
    public void Add(Candidate candidate);
}
