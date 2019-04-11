package com.mingliang.recruit.controller;

import com.mingliang.recruit.model.*;
import com.mingliang.recruit.service.impl.AdminServiceImpl;
import com.mingliang.recruit.service.impl.CandidateServiceImpl;
import com.mingliang.recruit.service.impl.CompanyServiceImpl;
import com.mingliang.recruit.service.impl.ForbidUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
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
    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    @Autowired
    private Company company;

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
    public ModelAndView memberlist(String sign,String usertype,HttpServletRequest request,ModelAndView modelAndView){
        if(sign.equals("1")) {
            List<Candidate> candidateList = candidateServiceImpl.FindAllCandidates(sign);
            if (candidateList == null || candidateList.size() == 0) {
                modelAndView.addObject("message", "没有求职者信息");
            }
            modelAndView.addObject("candidateList", candidateList);
            modelAndView.addObject("listnumber", candidateList.size());
            modelAndView.setViewName("Admin/member-list");
        }
        else{
            List<ForbidUser> forbidUserList=forbidUserServiceImpl.FindForbidUsersListByUsertype(usertype);
            if(forbidUserList.size()==0||forbidUserList==null) {
                modelAndView.addObject("message", "没有封号用户信息");
            }
            modelAndView.addObject("forbidUserList",forbidUserList);
            modelAndView.addObject("listnumber", forbidUserList.size());
            modelAndView.setViewName("Admin/member-del");
        }
        return modelAndView;
    }
    @GetMapping("ForbidReason")
    public ModelAndView ForbidReason(String Userid,String Usertype,ModelAndView modelAndView){
        modelAndView.addObject("Userid",Userid);
        modelAndView.addObject("Usertype",Usertype);
        modelAndView.setViewName("Admin/forbidreason");
        return modelAndView;
    }

    @GetMapping("/AddForbidUser")
    @ResponseBody
    public void AddForbidUser(String Userid,String forbidreason,String usertype,HttpServletRequest request,ModelAndView modelAndView){
        HttpSession session=request.getSession(true);
        String Adminid=session.getAttribute("AdminID").toString();
        forbidUser.setAdminid(Adminid);
        forbidUser.setUserid(Userid);
        forbidUser.setForbiddate(new Date());
        forbidUser.setReason(forbidreason);
        forbidUser.setUsertype(usertype);
        if(usertype.equals("0")) {
            candidate.setCandidateId(Userid);
            candidate.setSign("0");
            candidateServiceImpl.Change(candidate);
        }
        else
        {
            company.setCompanyid(Userid);
            company.setForbidsign("0");
            companyServiceImpl.ChangeForbidSign(company);
        }
        forbidUserServiceImpl.AddForbidUser(forbidUser);
    }
    @GetMapping("/RelieveForbidUser")
    public ModelAndView RelieveForbidUser(String Userid,String usertype,HttpServletRequest request,ModelAndView modelAndView){
        HttpSession session=request.getSession(true);
        if(usertype.equals("candidate")) {
            candidate.setCandidateId(Userid);
            candidate.setSign("1");
            candidateServiceImpl.Change(candidate);
            forbidUserServiceImpl.DeleteForbidUser(Userid);
            List<Candidate> candidateList = candidateServiceImpl.FindAllCandidates("0");
            if (candidateList == null || candidateList.size() == 0) {
                modelAndView.addObject("message", "没有求职者信息");
            }
            modelAndView.addObject("candidateList", candidateList);
            modelAndView.addObject("listnumber", candidateList.size());
            modelAndView.setViewName("Admin/member-list");

        }else{
            company.setCompanyid(Userid);
            company.setForbidsign("1");
            companyServiceImpl.ChangeForbidSign(company);
            forbidUserServiceImpl.DeleteForbidUser(Userid);
            List<Company> companyList = companyServiceImpl.SearchCompanyListBySign("1","0");
            if (companyList == null || companyList.size() == 0) {
                modelAndView.addObject("message", "没有相关企业信息");
            }
            modelAndView.addObject("sign","0");
            modelAndView.addObject("companyList", companyList);
            modelAndView.addObject("listnumber", companyList.size());
            modelAndView.setViewName("Admin/company-brand");
        }
        return modelAndView;
    }

    //Userid:搜索用户的id，searchusertype搜索用户的类别，usersign搜索用户的状态
    @GetMapping("/SearchUser")
    public ModelAndView SearchUser(String Userid,String searchusertype,String usersign,HttpServletRequest request,ModelAndView modelAndView){
        if(searchusertype.equals("candidate")&&usersign.equals("1")){
            List<Candidate> candidateList = candidateServiceImpl.SearchList("%"+Userid+"%",usersign);
            if (candidateList == null || candidateList.size() == 0) {
                modelAndView.addObject("message", "没有该用户的信息");
            }
                modelAndView.addObject("candidateList", candidateList);
                modelAndView.addObject("listnumber", candidateList.size());
                modelAndView.setViewName("Admin/member-list");
        }else if(searchusertype.equals("candidate")&&usersign.equals("0")){
            List<ForbidUser> forbidUserList=forbidUserServiceImpl.SearchList("%"+Userid+"%",usersign);
            if(forbidUserList.size()==0||forbidUserList==null) {
                modelAndView.addObject("message", "没有封号用户信息");
            }
            modelAndView.addObject("forbidUserList",forbidUserList);
            modelAndView.addObject("listnumber", forbidUserList.size());
            modelAndView.setViewName("Admin/member-del");
        }
        return modelAndView;
    }

    @GetMapping("/companylist")
    public ModelAndView companylist(String sign,HttpServletRequest request,ModelAndView modelAndView){
        List<Company> companyList;
        if(sign.equals("-1")){
            companyList=companyServiceImpl.SearchCompanyListByCheckSign(sign);
        }else if(sign.equals("1")){
            companyList=companyServiceImpl.SearchCompanyListBySign("1","1");
        }
        else{
            companyList=companyServiceImpl.SearchCompanyListBySign("1","0");
        }
        modelAndView.addObject("sign",sign);
        if(companyList==null||companyList.size()==0){
            modelAndView.addObject("message", "没有相关企业信息");
        }
        modelAndView.addObject("companyList", companyList);
        modelAndView.addObject("listnumber", companyList.size());
        modelAndView.setViewName("Admin/company-brand");
        return modelAndView;
    }
//查看company信息
    @GetMapping("/AdminShowCompany")
    public ModelAndView ShowCompany(String usertype, String companyid, ModelAndView modelAndView, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute("usertype", usertype);
        String CompanyId=companyid;
        session.setAttribute("company", companyServiceImpl.CompanyInformation(CompanyId));
        Company company = companyServiceImpl.CompanyPositions(CompanyId);
        List<Position> positions = company.getPositions();
        model.addAttribute("positions", positions);
        modelAndView.setViewName("companyInformationShow");
        return modelAndView;
    }
    //审核公司信息
    @GetMapping("/ReviewCompanyInformation")
    public ModelAndView ReviewCompanyInformation(String CompanyId,ModelAndView modelAndView){
        company=companyServiceImpl.CompanyInformation(CompanyId);
        company.setCompanylogo("/upload/"+CompanyId+"/"+company.getCompanylogo());
        company.setCompanylicense("/upload/"+CompanyId+"/"+company.getCompanylicense());
        modelAndView.addObject("company",company);
        modelAndView.setViewName("Admin/reviewcompanyinformation");
        return modelAndView;
    }
    @GetMapping("/CheckCompanySign")
    public ModelAndView CheckCompanySign(String CompanyId,String signtype,HttpServletRequest request,ModelAndView modelAndView){
        if(signtype.equals("-1")){
            companyServiceImpl.DeleteCompany(CompanyId);
        }
        else{
            company.setCompanyid(CompanyId);
            company.setChecksign(true);
            companyServiceImpl.UpdateCompany(company);
        }
        modelAndView.addObject("sign","-1");
        modelAndView.setViewName("Admin/company-brand");
        return modelAndView;
    }
}
