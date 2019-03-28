package com.mingliang.recruit.service.impl;

import com.mingliang.recruit.mapper.ResumeMapper;
import com.mingliang.recruit.model.Resume;
import com.mingliang.recruit.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;
    @Override
    public void Add(Resume resume) {
        resumeMapper.insert(resume);
    }

    @Override
    public Resume ResumeResult(String CandidateId) {
        return resumeMapper.selectByPrimaryKey(CandidateId);
    }

    @Override
    public Boolean CheckResume(String candidateId) {
        if(resumeMapper.selectByPrimaryKey(candidateId)!=null)
            return true; //简历已存在
        else
            return false;//简历不存在
    }

    @Override
    public void UpdateResume(Resume resume) {
        resumeMapper.updateByPrimaryKey(resume);
    }
}
