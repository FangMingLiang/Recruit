package com.mingliang.recruit.model;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Resume {
    private String candidateid;

    private String name;

    private String sex;

    private String portrait;

    private Date birthdate;

    private String phonenumber;

    private String email;

    private String education;

    private String expectedcity;

    private String expectedposition;

    private String expectedpositiontype;

    private String expectedsalary;

    private String school;

    private String major;

    private String jobcompanyname;

    private String jobname;

    private Date jobfirstdate;

    private Date joblastdate;

    private String projectname;

    private String projectdonselfdescribe;

    private Date projectfirstdate;

    private Date projectlastdate;

    private String projectwholedescribe;

    private String selfappraisal;

    public String getCandidateid() {
        return candidateid;
    }

    public void setCandidateid(String candidateid) {
        this.candidateid = candidateid == null ? null : candidateid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getExpectedcity() {
        return expectedcity;
    }

    public void setExpectedcity(String expectedcity) {
        this.expectedcity = expectedcity == null ? null : expectedcity.trim();
    }

    public String getExpectedposition() {
        return expectedposition;
    }

    public void setExpectedposition(String expectedposition) {
        this.expectedposition = expectedposition == null ? null : expectedposition.trim();
    }

    public String getExpectedpositiontype() {
        return expectedpositiontype;
    }

    public void setExpectedpositiontype(String expectedpositiontype) {
        this.expectedpositiontype = expectedpositiontype == null ? null : expectedpositiontype.trim();
    }

    public String getExpectedsalary() {
        return expectedsalary;
    }

    public void setExpectedsalary(String expectedsalary) {
        this.expectedsalary = expectedsalary == null ? null : expectedsalary.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getJobcompanyname() {
        return jobcompanyname;
    }

    public void setJobcompanyname(String jobcompanyname) {
        this.jobcompanyname = jobcompanyname == null ? null : jobcompanyname.trim();
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname == null ? null : jobname.trim();
    }

    public Date getJobfirstdate() {
        return jobfirstdate;
    }

    public void setJobfirstdate(Date jobfirstdate) {
        this.jobfirstdate = jobfirstdate;
    }

    public Date getJoblastdate() {
        return joblastdate;
    }

    public void setJoblastdate(Date joblastdate) {
        this.joblastdate = joblastdate;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getProjectdonselfdescribe() {
        return projectdonselfdescribe;
    }

    public void setProjectdonselfdescribe(String projectdonselfdescribe) {
        this.projectdonselfdescribe = projectdonselfdescribe == null ? null : projectdonselfdescribe.trim();
    }

    public Date getProjectfirstdate() {
        return projectfirstdate;
    }

    public void setProjectfirstdate(Date projectfirstdate) {
        this.projectfirstdate = projectfirstdate;
    }

    public Date getProjectlastdate() {
        return projectlastdate;
    }

    public void setProjectlastdate(Date projectlastdate) {
        this.projectlastdate = projectlastdate;
    }

    public String getProjectwholedescribe() {
        return projectwholedescribe;
    }

    public void setProjectwholedescribe(String projectwholedescribe) {
        this.projectwholedescribe = projectwholedescribe == null ? null : projectwholedescribe.trim();
    }

    public String getSelfappraisal() {
        return selfappraisal;
    }

    public void setSelfappraisal(String selfappraisal) {
        this.selfappraisal = selfappraisal == null ? null : selfappraisal.trim();
    }
}