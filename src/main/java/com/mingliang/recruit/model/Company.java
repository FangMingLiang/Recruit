package com.mingliang.recruit.model;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Company {
    private String companyid;

    private String companypassword;

    private String companyname;

    private String companylogo;

    private String companylicense;

    private String companywebsite;

    private String companycity;

    private String companyindustry;

    private String companyscale;

    private String companyprofile;

    private Date establishdate;

    private Boolean checksign;

    private List<Position> positions;
    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }

    public String getCompanypassword() {
        return companypassword;
    }

    public void setCompanypassword(String companypassword) {
        this.companypassword = companypassword == null ? null : companypassword.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo == null ? null : companylogo.trim();
    }

    public String getCompanylicense() {
        return companylicense;
    }

    public void setCompanylicense(String companylicense) {
        this.companylicense = companylicense == null ? null : companylicense.trim();
    }

    public String getCompanywebsite() {
        return companywebsite;
    }

    public void setCompanywebsite(String companywebsite) {
        this.companywebsite = companywebsite == null ? null : companywebsite.trim();
    }

    public String getCompanycity() {
        return companycity;
    }

    public void setCompanycity(String companycity) {
        this.companycity = companycity == null ? null : companycity.trim();
    }

    public String getCompanyindustry() {
        return companyindustry;
    }

    public void setCompanyindustry(String companyindustry) {
        this.companyindustry = companyindustry == null ? null : companyindustry.trim();
    }

    public String getCompanyscale() {
        return companyscale;
    }

    public void setCompanyscale(String companyscale) {
        this.companyscale = companyscale == null ? null : companyscale.trim();
    }

    public String getCompanyprofile() {
        return companyprofile;
    }

    public void setCompanyprofile(String companyprofile) {
        this.companyprofile = companyprofile == null ? null : companyprofile.trim();
    }

    public Date getEstablishdate() {
        return establishdate;
    }

    public void setEstablishdate(Date establishdate) {
        this.establishdate = establishdate;
    }

    public Boolean getChecksign() {
        return checksign;
    }

    public void setChecksign(Boolean checksign) {
        this.checksign = checksign;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}