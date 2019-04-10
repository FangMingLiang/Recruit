package com.mingliang.recruit.controller;

import com.mingliang.recruit.model.Candidate;
import com.mingliang.recruit.model.Resume;
import com.mingliang.recruit.service.impl.CandidateServiceImpl;
import com.mingliang.recruit.service.impl.ResumeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ResumeController {
    @Autowired
    private CandidateServiceImpl candidateServiceImpl;
    @Autowired
    private ResumeServiceImpl resumeServiceImpl;
    @Autowired
    private Resume resume;
    @Autowired
    private Candidate candidate;
    @RequestMapping("/resumepreview")
    public ModelAndView resume(HttpServletRequest request, ModelAndView modelAndView) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("UserID") != null) {
            modelAndView.setViewName("resumeshow");
        } else {
            modelAndView.addObject("error", "您还未登录，请登录！");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
    @RequestMapping("updateResume")
    public ModelAndView updateResume(HttpServletRequest request,ModelAndView modelAndView){
        HttpSession session=request.getSession(true);
        String Candidateid=session.getAttribute("UserID").toString();
        modelAndView=ReturnResume(Candidateid,"0",request,modelAndView);
        modelAndView.setViewName("resumepage");
        return modelAndView;
    }
    @GetMapping("/resumeshow")
    public ModelAndView resumeshow(String CandidateId,String usertype,HttpServletRequest request,ModelAndView modelAndView){
        modelAndView=ReturnResume(CandidateId,usertype,request,modelAndView);
        modelAndView.setViewName("resumeshow");
        return modelAndView;
    }
    public ModelAndView ReturnResume(String CandidateId,String usertype,HttpServletRequest request,ModelAndView modelAndView){

        resume=resumeServiceImpl.ResumeResult(CandidateId);//获取用户类型
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //基本信息
        modelAndView.addObject("usertype",usertype);//返回姓名
        modelAndView.addObject("name",resume.getName());//返回姓名
        modelAndView.addObject("sex",resume.getSex());//返回性别
        modelAndView.addObject("portrait","/upload/"+CandidateId+"/"+resume.getPortrait());//返回头像地址
        modelAndView.addObject("birthdate",sdf.format(resume.getBirthdate()));//返回出生日期
        modelAndView.addObject("phone",resume.getPhonenumber());//返回联系电话
        modelAndView.addObject("email",resume.getEmail());//返回邮箱
        modelAndView.addObject("scale",resume.getEducation());//返回学历
        modelAndView.addObject("school",resume.getSchool());//返回毕业学校
        modelAndView.addObject("major",resume.getMajor());//返回所学专业
        //期望工作
        modelAndView.addObject("city",resume.getExpectedcity());//返回期望城市
        modelAndView.addObject("type",resume.getExpectedpositiontype());//返回期望类型
        modelAndView.addObject("salary",resume.getExpectedsalary());//返回期望工资
        modelAndView.addObject("position",resume.getExpectedposition());//返回期望职位
        //工作经历
        modelAndView.addObject("jobfirstdate",sdf.format(resume.getJobfirstdate()));//返回就职开始时间
        modelAndView.addObject("joblastdate",sdf.format(resume.getJoblastdate()));//返回离职时间
        modelAndView.addObject("job_name",resume.getJobname());//返回任职职位名称
        modelAndView.addObject("company_name",resume.getJobcompanyname());//返回工作公司名称
        //项目经验
        modelAndView.addObject("project_name",resume.getProjectname());//返回项目名称
        modelAndView.addObject("projectfirstdate",sdf.format(resume.getProjectfirstdate()));//返回项目开始时间
        modelAndView.addObject("projectlastdate",sdf.format(resume.getProjectlastdate()));//返回项目结束时间
        modelAndView.addObject("project_describe_self",resume.getProjectdonselfdescribe());//返回本人负责项目的职务
        modelAndView.addObject("project_describe_all",resume.getProjectwholedescribe());//返回项目整体介绍
        //自我评价
        modelAndView.addObject("selfappraisal",resume.getSelfappraisal());//返回自我评价
        return modelAndView;
    }
    @RequestMapping("/ResumeInformation")
    public ModelAndView ResumeInformation(HttpServletRequest request, ModelAndView modelAndView,
                                    @RequestParam("companyLogo") MultipartFile portrait) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate= sdf.parse(request.getParameter("birthday"));//出生日期
        Date joblastdate=sdf.parse(request.getParameter("joblastdate"));//离职时间
        Date jobfirstdate=sdf.parse(request.getParameter("jobfirstdate"));//就职时间
        Date projectfirstdate=sdf.parse(request.getParameter("projectfirstdate"));//项目开始时间
        Date projectlastdate=sdf.parse(request.getParameter("projectlastdate"));//项目结束时间
        HttpSession session = request.getSession(true);
        String candidateid=session.getAttribute("UserID").toString();//手机号
        String CandidatePassword=session.getAttribute("UserPassword").toString();//密码
        String name = request.getParameter("name");//姓名
        upload(portrait, candidateid);//保存头像到本地
        String sex = request.getParameter("sex");//性别
        String phonenumber = request.getParameter("phone");//联系电话
        String email = request.getParameter("email");//常用邮箱
        String education = request.getParameter("select_scale_hidden");//学历
        String expectedcity = request.getParameter("city");//期望城市
        String expectedposition = request.getParameter("position");//期望职位
        String expectedpositiontype = request.getParameter("type");//期望工作类型
        String expectedsalary = request.getParameter("select_salary_hidden");//期望工资
        String school = request.getParameter("school");//毕业学校
        String major = request.getParameter("major");//专业
        String jobcompanyname = request.getParameter("company_name");//工作公司名称
        String jobname = request.getParameter("job_name");//工作职位
        String projectname = request.getParameter("project_name");//项目名称
        String projectdonselfdescribe = request.getParameter("project_describe_self");//项目中负责的部分
        String projectwholedescribe = request.getParameter("project_describe_all");//项目整体介绍
        String selfappraisal = request.getParameter("companyProfile");//自我评价
        candidate.setCandidateId(candidateid);
        candidate.setCandidatesPassword(CandidatePassword);
        candidate.setSign("1");
        candidate.setCreatedate(new Date());
        resume.setCandidateid(candidateid);
        resume.setName(name);
        resume.setSex(sex);
        resume.setPortrait(portrait.getOriginalFilename());
        resume.setBirthdate(birthdate);
        resume.setPhonenumber(phonenumber);
        resume.setEmail(email);
        resume.setEducation(education);
        resume.setExpectedcity(expectedcity);
        resume.setExpectedposition(expectedposition);
        resume.setExpectedpositiontype(expectedpositiontype);
        resume.setExpectedsalary(expectedsalary);
        resume.setSchool(school);
        resume.setMajor(major);
        resume.setJobcompanyname(jobcompanyname);
        resume.setJobname(jobname);
        resume.setJobfirstdate(jobfirstdate);
        resume.setJoblastdate(joblastdate);
        resume.setProjectname(projectname);
        resume.setProjectdonselfdescribe(projectdonselfdescribe);
        resume.setProjectfirstdate(projectfirstdate);
        resume.setProjectlastdate(projectlastdate);
        resume.setProjectwholedescribe(projectwholedescribe);
        resume.setSelfappraisal(selfappraisal);
        if(!resumeServiceImpl.CheckResume(candidateid)) {
            candidateServiceImpl.Add(candidate);
            resumeServiceImpl.Add(resume);
            modelAndView.addObject("error", "成功创建账号和简历，可以登录了哦！");
            modelAndView.setViewName("login");
            return modelAndView;
        }else{
            resumeServiceImpl.UpdateResume(resume);
            modelAndView=ReturnResume(candidateid,"0",request,modelAndView);
            modelAndView.setViewName("resumeshow");
            return modelAndView;
        }
    }
    public void upload(MultipartFile image,String companyId) {
        String basePath ="F:\\IdeaProjects\\recruit\\src\\main\\resources\\static\\upload\\"+companyId+"\\";
        System.out.println(basePath);
        File directory = new File(basePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            image.transferTo(new File(basePath + image.getOriginalFilename()));
        } catch (Exception e) {
            // TODO
            System.out.println("文件写入错误！");
        }
    }
}
