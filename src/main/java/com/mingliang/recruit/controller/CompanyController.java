package com.mingliang.recruit.controller;

import com.mingliang.recruit.model.Company;
import com.mingliang.recruit.model.Position;
import com.mingliang.recruit.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
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

    @RequestMapping("/companyInformationShow")
    public String companyInformationShow(){
        return "companyInformationShow";
    }
    @RequestMapping("/canInterviewResumes")
    public String canInterviewResumes(Model model){
        return "canInterviewResumes";
    }
    @RequestMapping("/create")
    public String creat(Model model){
        return "create";
    }

    @RequestMapping("/companyinformationUpdate")
    public ModelAndView companyinformationUpdate(String companyid,ModelAndView modelAndView){
        company=companyServiceImpl.CompanyInformation(companyid);
        modelAndView.addObject("company",company);
        modelAndView.setViewName("CompanyInformationupdate");
        return modelAndView;
    }
    @PostMapping("/CompanyInformationUpdateResult")
    public ModelAndView CompanyInformationUpdateResult(@RequestParam("companyLogo") MultipartFile CompanyLogoImage, @RequestParam("companyLicense") MultipartFile CompanyLicenseImage, HttpServletRequest request,ModelAndView modelAndView,Model model){
        HttpSession session = request.getSession(true);
        String CompanyId=session.getAttribute("UserID").toString();//公司注册手机号id
        if(!CompanyLogoImage.isEmpty()){
            upload(CompanyLogoImage,CompanyId);
            company.setCompanylogo(CompanyLogoImage.getOriginalFilename());
        }
        if(!CompanyLicenseImage.isEmpty())
        {
            upload(CompanyLicenseImage,CompanyId);
            company.setCompanylicense(CompanyLicenseImage.getOriginalFilename());
        }
        String CompanyName=request.getParameter("name");//公司名称
        String CompanyWebsite=request.getParameter("website");//网址
        String CompanyCity=request.getParameter("city");//城市
        String CompanyIndustry=request.getParameter("select_industry_hidden");//行业领域
        String CompanyScale=request.getParameter("select_scale_hidden");//公司规模
        String CompanyProfile=request.getParameter("companyProfile");//公司介绍
        company.setCompanyid(CompanyId);
        company.setCompanyname(CompanyName);
        company.setCompanywebsite(CompanyWebsite);
        company.setCompanycity(CompanyCity);
        company.setCompanyindustry(CompanyIndustry);
        company.setCompanyscale(CompanyScale);
        company.setCompanyprofile(CompanyProfile);
        companyServiceImpl.UpdateCompany(company);
        session.setAttribute("company", companyServiceImpl.CompanyInformation(CompanyId));
        Company company = companyServiceImpl.CompanyPositions(CompanyId);
        List<Position> positions = company.getPositions();
        model.addAttribute("positions", positions);
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
        company.setForbidsign("1");
        companyServiceImpl.Add(company);
        return "InformationSuccess";
    }
    //上传图片
    public void upload(MultipartFile image,String companyId) {
        String basePath ="E:\\IdeaProjects\\recruit\\src\\main\\resources\\static\\upload\\"+companyId+"\\";
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
