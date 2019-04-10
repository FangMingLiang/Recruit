package com.mingliang.recruit.controller;

import com.mingliang.recruit.model.Admin;
import com.mingliang.recruit.model.Candidate;
import com.mingliang.recruit.model.ForbidUser;
import com.mingliang.recruit.service.impl.AdminServiceImpl;
import com.mingliang.recruit.service.impl.CandidateServiceImpl;
import com.mingliang.recruit.service.impl.ForbidUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private ForbidUser forbidUser;
    @Autowired
    private ForbidUserServiceImpl forbidUserServiceImpl;

    @Autowired
    private Admin admin;
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private Candidate candidate;
    @Autowired
    private CandidateServiceImpl candidateServiceImpl;
    @RequestMapping("index")
    public String adminIndex(HttpServletRequest request){
        HttpSession session=request.getSession(true);
        if(session.getAttribute("AdminID")!=null)
            return "Admin/index";
        else
            return "Admin/adminlogin";

    }
    @RequestMapping("adminlogin")
    public String adminlogin(){
        return "Admin/adminlogin";
    }
    @PostMapping("AdminLoginResult")
    public ModelAndView AdminLoginResult(HttpServletRequest request,ModelAndView modelAndView){
        HttpSession session=request.getSession(true);
        String adminid=request.getParameter("adminlogin");
        String adminpassword=request.getParameter("adminpassword");
        admin=adminServiceImpl.AdminLoginResult(adminid);
        if(admin!=null) {
            if (adminpassword.equals(admin.getAdminpassword())) {
                session.setAttribute("AdminID", adminid);
                modelAndView.setViewName("admin/index");
                return modelAndView;
            } else {
                modelAndView.addObject("message", "密码或用户名错误！");
                modelAndView.setViewName("Admin/adminlogin");
                return modelAndView;
            }
        }else{
            modelAndView.addObject("message", "不存在该用户!");
            modelAndView.setViewName("Admin/adminlogin");
            return modelAndView;
        }
    }
    @GetMapping("memberlist")
    public ModelAndView memberlist(String sign,HttpServletRequest request,ModelAndView modelAndView){
        List<Candidate> candidateList=candidateServiceImpl.FindAllCandidates(sign);
        if(candidateList==null||candidateList.size()==0)
        {
            modelAndView.addObject("message","没有求职者信息");
        }
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        modelAndView.addObject("candidateList",candidateList);
        if(sign.equals("1"))
            modelAndView.setViewName("Admin/member-list");
        else
            modelAndView.setViewName("Admin/member-del");
        return modelAndView;
    }
    @GetMapping("ForbidReason")

    public ModelAndView ForbidReason(String Userid,ModelAndView modelAndView){
        modelAndView.addObject("Userid",Userid);
        modelAndView.setViewName("Admin/forbidreason");
        return modelAndView;
    }

    @RequestMapping("/AddForbidUser")
    @ResponseBody
    public void AddForbidUser(String Userid,String forbidreason,HttpServletRequest request,ModelAndView modelAndView){
        HttpSession session=request.getSession(true);
        String Adminid=session.getAttribute("AdminID").toString();
        forbidUser.setAdminid(Adminid);
        forbidUser.setUserid(Userid);
        forbidUser.setForbiddate(new Date());
        forbidUser.setReason(forbidreason);
        forbidUserServiceImpl.AddForbidUser(forbidUser);

    }

}
