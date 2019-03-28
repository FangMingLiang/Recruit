package com.mingliang.recruit.controller;

import com.mingliang.recruit.model.Candidate;
import com.mingliang.recruit.model.Company;
import com.mingliang.recruit.service.CandidateService;
import com.mingliang.recruit.service.impl.CandidateServiceImpl;
import com.mingliang.recruit.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Map;


@Controller
public class CandidateController {
    @Autowired
    private CandidateServiceImpl candidateServiceImpl;
    @Autowired
    private Candidate candidate;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @Autowired
    private Company company;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request,ModelAndView modelAndView){
        HttpSession session = request.getSession(true);
        if (session.getAttribute("UserID")!=null){
            modelAndView.setViewName("index");
        }else {
            modelAndView.addObject("error","您还未登录，请登录！");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/privacy")
    public String privacy(){
        return "privacy";
    }

    @PostMapping("/RegisterResult")
    public String register( HttpServletRequest request){
        String type=request.getParameter("type");
        HttpSession session = request.getSession(true);
        if (type.equals("0")){
            String CandidateId=request.getParameter("email");
            String CandidatePassword=request.getParameter("password");
            session.setAttribute("UserID",CandidateId);
            session.setAttribute("UserPassword",CandidatePassword);
            return "resumepage";
        }else{
            String CompanyId=request.getParameter("email");
            String CompanyPassword=request.getParameter("password");
            session.setAttribute("UserID",CompanyId);
            session.setAttribute("UserPassword",CompanyPassword);
            return "CompanyInformation";
        }
    }

    @RequestMapping("/LoginResult")
    @ResponseBody
    public ModelAndView  LoginResult(HttpServletRequest request, ModelAndView modelAndView) {
        String type = request.getParameter("type");
        String UserId = request.getParameter("email");
        String UserPassword = request.getParameter("password");
        HttpSession session = request.getSession(true);
        try {
            if (type.equals("0")) {

                candidate = candidateServiceImpl.LoginResult(UserId);
                if (UserPassword.equals(candidate.getCandidatesPassword())) {
                    session.setAttribute("UserID", UserId);
                    session.setAttribute("UserPassword", UserPassword);
                    modelAndView.setViewName("index");
                    return modelAndView;
                } else {
                    modelAndView.addObject("error", "求职者的用户名或密码错误，请重新输入！");
                    modelAndView.setViewName("login");
                    return modelAndView;
                }
            } else {
                company = companyServiceImpl.LoginResult(UserId);
                if (company.getChecksign() == false) {
                    modelAndView.addObject("error", "企业认证还未通过，请耐心等待审核，审核通过才能登录！");
                    modelAndView.setViewName("login");
                    return modelAndView;
                }
                if (UserPassword.equals(company.getCompanypassword())) {
                    session.setAttribute("sign", true);
                    modelAndView.setViewName("companyInformationShow");
                    return modelAndView;
                } else {
                    modelAndView.addObject("error", "企业的用户名或密码错误，请重新输入！");
                    modelAndView.setViewName("login");
                    return modelAndView;
                }
            }
        }catch (NullPointerException e){
            modelAndView.addObject("error","不存在该用户名！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }


//    @PostMapping("/imageUpload")
//    public String upload(@RequestParam("company_image") MultipartFile image,
//                         HttpServletRequest request) {
//        String basePath ="F:\\IdeaProjects\\recruit\\src\\main\\resources\\static\\upload\\";
//        System.out.println(basePath);
//        File directory = new File(basePath);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//        try {
//            image.transferTo(new File(basePath + image.getOriginalFilename()));
//        } catch (Exception e) {
//            // TODO
//            System.out.println("文件写入错误！");
//        }
//        return "success";
//    }


}

