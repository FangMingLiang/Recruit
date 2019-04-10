package com.mingliang.recruit.model;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Candidate {
    private String candidateId;

    private String candidatesPassword;

    private String sign;

    private Date createdate;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}