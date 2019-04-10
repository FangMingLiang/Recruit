package com.mingliang.recruit.service;

import com.mingliang.recruit.model.Candidate;

import java.util.List;

public interface CandidateService {
    public Candidate LoginResult(String CandidateId);
    public void Add(Candidate candidate);
    public List<Candidate> FindAllCandidates(String sign);
}
