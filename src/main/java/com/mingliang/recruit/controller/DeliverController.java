package com.mingliang.recruit.controller;

import com.mingliang.recruit.model.*;
import com.mingliang.recruit.service.impl.CompanyServiceImpl;
import com.mingliang.recruit.service.impl.DeliverServiceImpl;
import com.mingliang.recruit.service.impl.InterviewServiceImpl;
import com.mingliang.recruit.service.impl.ResumeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DeliverController {
    @Autowired
    private DeliverServiceImpl deliverServiceImpl;

    @Autowired
    private Deliver deliver;
    @Autowired
    private InterviewServiceImpl interviewServiceImpl;

    @Autowired
    private Interview interview;

    @Autowired
    private ResumeServiceImpl resumeServiceImpl;

    @Autowired
    private Resume resume ;

    @Autowired
    private Position position;

    @Autowired
    private CompanyServiceImpl companyServiceImpl;
    @PostMapping("/AddDeliver")
    @ResponseBody
    public String AddDeliver(String positionid,String userid) throws Exception{
        /* Candidate candidate=new Candidate();*/
        System.out.println(positionid);
        System.out.println(userid);
        deliver.setPositionid(Integer.valueOf(positionid));
        deliver.setCandidateid(userid);
        deliver.setDeliverdate(new Date());
        deliver.setResultsign("0");
        deliverServiceImpl.AddDeliver(deliver);
        return "index";
    }
    @GetMapping ("/ShowDelivery")
    public ModelAndView ShowDelivery(String CandidateId, ModelAndView modelAndView) throws ParseException {
        List<Position> deliverList =deliverServiceImpl.DeliverList(CandidateId);
        if(deliverList==null||deliverList.size()==0){
            modelAndView.addObject("Tips","您最近一个月还没有投递过简历哦!\n" +
                    "(*^__^*)");
            modelAndView.setViewName("delivershow");
            return modelAndView;
        }
        for(int i=0;i<deliverList.size();i++)
        {
            Date deliverdate=deliverList.get(i).getDeliverdate();
            deliverList.get(i).setCreatedate(TransformTime(deliverdate));
        }
        modelAndView.addObject("deliverList",deliverList);
        modelAndView.setViewName("delivershow");
        return modelAndView;
    }

    @GetMapping("/CompanyShowDelivery")
    public ModelAndView CompanyShowDelivery(String resultsign,String type,HttpServletRequest request,ModelAndView modelAndView){
        return ShowData(resultsign,type,request,modelAndView);
    }
    @PostMapping("/DeliverResult")
    public ModelAndView DeliverResult(HttpServletRequest request,ModelAndView modelAndView){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//转换时间格式
        HttpSession session=request.getSession(true);
        String companyid=session.getAttribute("UserID").toString();
        Company company=companyServiceImpl.CompanyInformation(companyid);//获取公司id
        String companyname=company.getCompanyname();//获取公司名称
        String candidateid=request.getParameter("candidateid");//获取求职者id
        String positionid=request.getParameter("positionid");
        String subject=request.getParameter("subject");//获取职位名称
        String interTime=request.getParameter("interTime");//获取面试时间
        String interAdd=request.getParameter("interAdd");//获取面试地点
        String linkMan=request.getParameter("linkMan");//获取面试联系人
        String linkPhone=request.getParameter("linkPhone");//获取面试联系人电话
        String content=request.getParameter("content");//获取补充内容
        interview.setCandidateid(candidateid);
        interview.setCompanyid(companyid);
        interview.setInterviewname(subject);
        interview.setInterviewtime(interTime);
        interview.setInterviewaddress(interAdd);
        interview.setLinkman(linkMan);
        interview.setLinkphone(linkPhone);
        interview.setContent(content);
        interview.setCreatetime(sdf.format(new Date()));
        interview.setSign("0");
        interview.setCompanyname(companyname);
        interviewServiceImpl.AddInterview(interview);
        deliverServiceImpl.ChangeSign(candidateid,positionid,"1");
        return ShowData("0","resume",request,modelAndView);
    }
    @PostMapping("/RefuseDeliver")
    public ModelAndView RefuseDeliver(HttpServletRequest request,ModelAndView modelAndView){
        String candidateid=request.getParameter("refuse_candidateid");//获取求职者id
        String positionid=request.getParameter("refuse_positionid");
        deliverServiceImpl.ChangeSign(candidateid,positionid,"-1");
        return ShowData("0","resume",request,modelAndView);
    }
    //返回简历或职位数据
    public ModelAndView ShowData(String resultsign,String type,HttpServletRequest request,ModelAndView modelAndView){
        String messageTag="";
        int listnumber;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//转换时间格式
        HttpSession session=request.getSession(true);
        String CompanyId=session.getAttribute("UserID").toString();
        Company company = companyServiceImpl.CompanyPositions(CompanyId);
        List<Position> positions = company.getPositions();//获取该公司的所有职位
        if(type.equals("resume")){
            List<Resume> ResumeList=new ArrayList<Resume>();//创建list返回前台
            for(int i=0;i<positions.size();i++)
            {
                String positionname=positions.get(i).getPositionname();
                int positionid=positions.get(i).getPositionid();
                List<Deliver> DeliverCandiateIdList=deliverServiceImpl.FindCandidateByPositionId(positionid,resultsign);
                for(int j=0;j<DeliverCandiateIdList.size();j++){
                    String candidateid=DeliverCandiateIdList.get(j).getCandidateid();
                    String deliverdate=sdf.format(DeliverCandiateIdList.get(j).getDeliverdate());
                    resume=resumeServiceImpl.ResumeResult(candidateid);
                    resume.setProjectwholedescribe(deliverdate);
                    resume.setProjectdonselfdescribe(String.valueOf(positionid));
                    resume.setSelfappraisal(positionname);
                    ResumeList.add(resume);
                }
            }
            if(resultsign.equals("0"))
            {
                messageTag="待处理简历";
            }
            else if(resultsign.equals("1")){
                messageTag = "已通知面试";
            }
            else{
                messageTag = "不合适";
            }
            listnumber=ResumeList.size();
            modelAndView.addObject("ResumeList",ResumeList);
        }
        else{
            List<Position> PositionList=new ArrayList<Position>();//创建list返回前台
            for(int i=0;i<positions.size();i++)
            {
                String sign=positions.get(i).getSign().toString();
                if(sign.equals(resultsign)){
                    PositionList.add(positions.get(i));//将符合条件的加入list，
                }
            }
            if(resultsign.equals("1"))
                messageTag="有效职位";
            else
                messageTag="已下架职位";
            listnumber=PositionList.size();
            modelAndView.addObject("PositionList",PositionList);//传回前台
        }
        modelAndView.addObject("messageTag",messageTag);
        modelAndView.addObject("listnumber",listnumber);
        modelAndView.addObject("type",type);
        modelAndView.setViewName("canInterviewResumes");
        return modelAndView;
    }
    //获取时间天数差
    public String TransformTime (Date date) throws ParseException {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        Date d1=sdf.parse(sdf.format(date));
        Date d2=sdf.parse(sdf.format(new Date()));
        long timeStr=(d2.getTime()-d1.getTime()+1000000)/(60*60*24*1000);
        return String.valueOf(timeStr);
    }
}
