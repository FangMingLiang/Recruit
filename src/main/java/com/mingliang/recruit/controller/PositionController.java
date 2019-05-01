package com.mingliang.recruit.controller;

import com.mingliang.recruit.model.Company;
import com.mingliang.recruit.model.Deliver;
import com.mingliang.recruit.model.Position;
import com.mingliang.recruit.service.impl.CompanyServiceImpl;
import com.mingliang.recruit.service.impl.DeliverServiceImpl;
import com.mingliang.recruit.service.impl.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PositionController {
    @Autowired
    private PositionServiceImpl positionServiceImpl;

    @Autowired
    private Position position;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @Autowired
    private Company company;

    @Autowired
    private DeliverServiceImpl deliverServiceImpl;
    @Autowired
    private Deliver deliver;
    @PostMapping("CreatePosition")
    public String CreatePosition(HttpServletRequest request) {
        HttpSession session=request.getSession(true);
        String CompanyId = session.getAttribute("UserID").toString();
        String positionType = request.getParameter("positionType");//获取职位类别
        String positionName = request.getParameter("positionName");//获取职位名称
        String department = request.getParameter("department");//获取所属部门
        String jobNature = request.getParameter("jobNature");//获取工作属性
        String salaryMax = request.getParameter("salaryMax");//获取最高月薪
        String salaryMin = request.getParameter("salaryMin");//获取最低月薪
        String workAddress1 = request.getParameter("workAddress1");//获取工作城市
        String experience = request.getParameter("workYear");//获取工作经验
        String education = request.getParameter("education");//获取学历要求
        String positionAdvantage = request.getParameter("positionAdvantage");//获取职位诱惑
        String positionDetail = request.getParameter("positionDetail");//获取职位描述
        String positionAddress = request.getParameter("positionAddress");//获取详细的工作地址
        position.setCompanyid(CompanyId);
        position.setPositiontype(positionType);
        position.setDepartment(department);
        position.setPositionname(positionName);
        position.setJobnature(jobNature);
        position.setSalary(salaryMin + "K-" + salaryMax + "K");
        position.setWorkaddress1(workAddress1);
        position.setExperience(experience);
        position.setEducation(education);
        position.setPositionadvantage(positionAdvantage);
        position.setPositiondetail(positionDetail);
        position.setPositionaddress(positionAddress);
        position.setSign("1");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        position.setCreatedate(df.format(new Date()));
        positionServiceImpl.AddPosition(position);
        return "createsuccess";
    }

    @GetMapping("ShowPosition")
    public ModelAndView ShowPosition (String positionid,String usertype,ModelAndView modelAndView,HttpServletRequest request){
        position=positionServiceImpl.PositionInformation(Integer.valueOf(positionid));
        String companyid=position.getCompanyid();
        company=companyServiceImpl.CompanyInformation(companyid);
        modelAndView.addObject("usertype",usertype);
        modelAndView.addObject("position",position);
        modelAndView.addObject("company",company);
        if(usertype.equals("0")){
            HttpSession session=request.getSession();
            String Candidateid=session.getAttribute("UserID").toString();
            int status=deliverServiceImpl.DeliverStatus(Candidateid,positionid);
            if(status>0){
                modelAndView.addObject("deliverSign","1");
            }
            modelAndView.setViewName("positionshow");
            return modelAndView;
        }
        modelAndView.setViewName("positionshow");
        return modelAndView;
    }
}
