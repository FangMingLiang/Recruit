package com.mingliang.recruit.model;

import org.springframework.stereotype.Component;

@Component
public class Candidate {
    private String candidateId;

    private String candidatesPassword;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId == null ? null : candidateId.trim();
    }

    public String getCandidatesPassword() {
        return candidatesPassword;
    }

    public void setCandidatesPassword(String candidatesPassword) {
        this.candidatesPassword = candidatesPassword == null ? null : candidatesPassword.trim();
    }
}