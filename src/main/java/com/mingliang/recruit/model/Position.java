package com.mingliang.recruit.model;

import org.springframework.stereotype.Component;

@Component
public class Position {
    private Integer positionid;

    private String companyid;

    private String positiontype;

    private String positionname;

    private String department;

    private String jobnature;

    private String salary;

    private String workaddress1;

    private String experience;

    private String education;

    private String positionadvantage;

    private String positiondetail;

    private String positionaddress;

    private String sign;

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }

    public String getPositiontype() {
        return positiontype;
    }

    public void setPositiontype(String positiontype) {
        this.positiontype = positiontype == null ? null : positiontype.trim();
    }

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname == null ? null : positionname.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getJobnature() {
        return jobnature;
    }

    public void setJobnature(String jobnature) {
        this.jobnature = jobnature == null ? null : jobnature.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getWorkaddress1() {
        return workaddress1;
    }

    public void setWorkaddress1(String workaddress1) {
        this.workaddress1 = workaddress1 == null ? null : workaddress1.trim();
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getPositionadvantage() {
        return positionadvantage;
    }

    public void setPositionadvantage(String positionadvantage) {
        this.positionadvantage = positionadvantage == null ? null : positionadvantage.trim();
    }

    public String getPositiondetail() {
        return positiondetail;
    }

    public void setPositiondetail(String positiondetail) {
        this.positiondetail = positiondetail == null ? null : positiondetail.trim();
    }

    public String getPositionaddress() {
        return positionaddress;
    }

    public void setPositionaddress(String positionaddress) {
        this.positionaddress = positionaddress == null ? null : positionaddress.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }
}