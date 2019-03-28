package com.mingliang.recruit.service;


import com.mingliang.recruit.model.Company;

public interface CompanyService {
    public Company LoginResult(String CompanyId);
    public void Add(Company company);
    public Company CompanyInformation(String CompanyId);
    public Company CompanyPositions(String CompanyId);
}
