package com.mingliang.recruit.service;

import com.mingliang.recruit.model.Resume;

public interface ResumeService {
    public void Add(Resume resume);
    public Resume ResumeResult(String CandidateId);
    public Boolean CheckResume(String candidateId);
    public void UpdateResume(Resume resume);
}
