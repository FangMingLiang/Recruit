package com.mingliang.recruit.controller;

import com.mingliang.recruit.model.Candidate;
import com.mingliang.recruit.model.Company;
import com.mingliang.recruit.model.Position;
import com.mingliang.recruit.service.CandidateService;
import com.mingliang.recruit.service.impl.CandidateServiceImpl;
import com.mingliang.recruit.service.impl.CompanyServiceImpl;
import com.mingliang.recruit.service.impl.PositionServiceImpl;
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
import java.util.Enumeration;
import java.util.List;
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
    private PositionServiceImpl positionServiceImpl;
    @Autowired
    private Company company;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, ModelAndView modelAndView, Model model) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("UserID") != null) {
            List<Position> positionList = positionServiceImpl.SelectAllPositions();
            model.addAttribute("positionlist", positionList);
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("error", "您还未登录，请登录！");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/seachlist")
    public String seachlist() {
        return "seachlist";
    }

    @RequestMapping("/Logout")
    public String Logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("UserID", null);
        session.setAttribute("UserPassword", null);
        return "login";
    }

    @RequestMapping("/privacy")
    public String privacy() {
        return "privacy";
    }

    @PostMapping("/RegisterResult")
    public ModelAndView register(HttpServletRequest request, ModelAndView modelAndView) {
        String type = request.getParameter("type");
        HttpSession session = request.getSession(true);
        String UserId = request.getParameter("email");
        String Userpassword = request.getParameter("password");
        if (candidateServiceImpl.LoginResult(UserId) != null || companyServiceImpl.LoginResult(UserId) != null) {
            modelAndView.addObject("error", "已存在该账号，请去登录！");
            modelAndView.setViewName("register");
            return modelAndView;
        }
        if (type.equals("0")) {
            session.setAttribute("UserID", UserId);
            session.setAttribute("UserPassword", Userpassword);
            modelAndView.setViewName("resumepage");
            return modelAndView;
        } else {
            session.setAttribute("UserID", UserId);
            session.setAttribute("UserPassword", Userpassword);
            modelAndView.setViewName("CompanyInformation");
            return modelAndView;
        }
    }

    @RequestMapping("/LoginResult")
//    @ResponseBody
    public ModelAndView LoginResult(HttpServletRequest request, ModelAndView modelAndView, Model model) {
        String type = request.getParameter("type");
        String UserId = request.getParameter("email");
        String UserPassword = request.getParameter("password");
        HttpSession session = request.getSession(true);
        session.setAttribute("usertype", type);
        try {
            if (type.equals("0")) {
                candidate = candidateServiceImpl.LoginResult(UserId);
                if (UserPassword.equals(candidate.getCandidatesPassword())) {
                    session.setAttribute("UserID", UserId);
                    session.setAttribute("UserPassword", UserPassword);
                    List<Position> positionList = positionServiceImpl.SelectAllPositions();
                    model.addAttribute("positionlist", positionList);
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
                    session.setAttribute("UserID", UserId);
                    session.setAttribute("UserPassword", UserPassword);
                    String CompanyId = UserId;
                    session.setAttribute("company", companyServiceImpl.CompanyInformation(CompanyId));
                    Company company = companyServiceImpl.CompanyPositions(CompanyId);
                    List<Position> positions = company.getPositions();
                    model.addAttribute("positions", positions);
                    modelAndView.setViewName("companyInformationShow");
                    return modelAndView;
                } else {
                    modelAndView.addObject("error", "企业的用户名或密码错误，请重新输入！");
                    modelAndView.setViewName("login");
                    return modelAndView;
                }
            }
        } catch (NullPointerException e) {
            modelAndView.addObject("error", "不存在该用户名！");
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @GetMapping("/ShowCompany")
    public ModelAndView ShowCompany(String usertype, String companyid, ModelAndView modelAndView, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        session.setAttribute("usertype", usertype);
        session.setAttribute("company", companyServiceImpl.CompanyInformation(companyid));
        Company company = companyServiceImpl.CompanyPositions(companyid);
        List<Position> positions = company.getPositions();
        model.addAttribute("positions", positions);
        modelAndView.setViewName("companyInformationShow");
        return modelAndView;
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

    @GetMapping("/Search")
    public ModelAndView Search(String searchtype, String searchcontent, HttpServletRequest request, ModelAndView modelAndView, Model model) {
        if (searchtype.equals("1"))//1：搜索职位 0：搜索公司
        {
            List<Position> SearchPositionList = positionServiceImpl.SearchPositionName("%" + searchcontent + "%");
            model.addAttribute("positionlist", SearchPositionList);
            modelAndView.addObject("SearchSign", "职位搜索");
        } else {
            List<Company> SearchComapnyist = companyServiceImpl.SearchCompanyName("%" + searchcontent + "%");
            model.addAttribute("companylist", SearchComapnyist);
            modelAndView.addObject("SearchSign", "公司搜索");
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

