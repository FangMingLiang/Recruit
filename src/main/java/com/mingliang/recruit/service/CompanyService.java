package com.mingliang.recruit.service;


import com.mingliang.recruit.model.Company;

import java.util.List;

public interface CompanyService {
    public Company LoginResult(String CompanyId);
    public void Add(Company company);
    public Company CompanyInformation(String CompanyId);
    public Company CompanyPositions(String CompanyId);
    public void UpdateCompany(Company company);
    public List<Company> SearchCompanyName(String CompanyName);//根据公司名称模糊查找
    public List<Company> SearchCompanyListBySign(String CheckSign,String ForbidSign);//根据标记查找list
    public List<Company> SearchCompanyListByCheckSign(String sign);//根据审核标记查找list

    //改变企业的封号标记
    public void ChangeForbidSign(Company company);

    public void DeleteCompany(String CompanyId);


}
