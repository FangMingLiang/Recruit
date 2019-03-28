package com.mingliang.recruit.controller;

import com.mingliang.recruit.mapper.CompanyMapper;
import com.mingliang.recruit.model.Company;
import com.mingliang.recruit.model.Position;
import com.mingliang.recruit.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CompanyController {
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @Autowired
    private Company company;

    @RequestMapping("/InformationSuccess")
    public String InformationSuccess(){
        return "InformationSuccess";
    }
    @RequestMapping("/create")
    public String creat(Model model){
        return "create";
    }
    @PostMapping("CreatePosition")
    public String CreatePosition(HttpServletRequest request){
        String CompanyId="1234567890";
        String positionType=request.getParameter("positionType");//获取职位类别
        String positionName=request.getParameter("positionName");//获取职位名称
        String department=request.getParameter("department");//获取所属部门
        String jobNature=request.getParameter("jobNature");//获取工作属性
        String salaryMax=request.getParameter("salaryMax");//获取最高月薪
        String salaryMin=request.getParameter("salaryMin");//获取最低月薪
        String workAddress1=request.getParameter("workAddress1");//获取工作城市
        String experience=request.getParameter("experience");//获取工作经验
        String education=request.getParameter("education");//获取学历要求
        String positionAdvantage=request.getParameter("positionAdvantage");//获取职位诱惑
        String positionDetail=request.getParameter("positionDetail");//获取职位描述
        String positionAddress=request.getParameter("positionAddress");//获取详细的工作地址

        return "createsuccess";
    }

    @RequestMapping("/companyInformationShow")
    public ModelAndView companyInformationShow(HttpServletRequest request,ModelAndView modelAndView,Model model){
        HttpSession session=request.getSession();
        String CompanyId="1234567890";
        session.setAttribute("company",companyServiceImpl.CompanyInformation(CompanyId));
        Company company=companyServiceImpl.CompanyPositions(CompanyId);
        List<Position> positions=company.getPositions();
        model.addAttribute("positions",positions);
        modelAndView.setViewName("companyInformationShow");
        return modelAndView;
    }
    @PostMapping("/CompanyInformationResult")
    public String CompanyInformationResult(@RequestParam("companyLogo") MultipartFile CompanyLogoImage, @RequestParam("companyLicense") MultipartFile CompanyLicenseImage, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String CompanyId=session.getAttribute("UserID").toString();//公司注册手机号id
        String CompanyPassword=session.getAttribute("UserPassword").toString();//公司账号密码        String CompanyName=request.getParameter("name");//公司名称
        upload(CompanyLogoImage,CompanyId);
        upload(CompanyLicenseImage,CompanyId);
        String CompanyName=request.getParameter("name");//公司名称
        String CompanyWebsite=request.getParameter("website");//网址
        String CompanyCity=request.getParameter("city");//城市
        String CompanyIndustry=request.getParameter("select_industry_hidden");//行业领域
        String CompanyScale=request.getParameter("select_scale_hidden");//公司规模
        String CompanyProfile=request.getParameter("companyProfile");//公司介绍
        company.setCompanyid(CompanyId);
        company.setCompanypassword(CompanyPassword);
        company.setCompanyname(CompanyName);
        company.setCompanylogo(CompanyLogoImage.getOriginalFilename());
        company.setCompanylicense(CompanyLicenseImage.getOriginalFilename());
        company.setCompanywebsite(CompanyWebsite);
        company.setCompanycity(CompanyCity);
        company.setCompanyindustry(CompanyIndustry);
        company.setCompanyscale(CompanyScale);
        company.setCompanyprofile(CompanyProfile);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());//获取当前的时间
        company.setEstablishdate(timeStamp);
        company.setChecksign(false);
        companyServiceImpl.Add(company);
        return "InformationSuccess";
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
