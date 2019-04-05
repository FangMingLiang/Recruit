package com.mingliang.recruit.model;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Deliver {
    private Integer deliverid;

    private String candidateid;

    private Integer positionid;

    private Date deliverdate;

    private String resultsign;

    public Integer getDeliverid() {
        return deliverid;
    }

    public void setDeliverid(Integer deliverid) {
        this.deliverid = deliverid;
    }

    public String getCandidateid() {
        return candidateid;
    }

    public void setCandidateid(String candidateid) {
        this.candidateid = candidateid == null ? null : candidateid.trim();
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public Date getDeliverdate() {
        return deliverdate;
    }

    public void setDeliverdate(Date deliverdate) {
        this.deliverdate = deliverdate;
    }

    public String getResultsign() {
        return resultsign;
    }

    public void setResultsign(String resultsign) {
        this.resultsign = resultsign == null ? null : resultsign.trim();
    }
}