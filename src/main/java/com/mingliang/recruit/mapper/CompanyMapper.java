package com.mingliang.recruit.mapper;

import com.mingliang.recruit.model.Company;

import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(String companyid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(String companyid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    Company selectpositionById(String companyid);

    List<Company> SearchCompanyName(String companyName);//根据公司名称模糊查找
    List<Company> SearchCompanyListBySign(String CheckSign,String ForbidSign);//根据标记查找list

    List<Company> SearchCompanyListByCheckSign(String sign);//根据审核标记查找list
}