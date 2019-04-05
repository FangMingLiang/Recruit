package com.mingliang.recruit.service.impl;

import com.mingliang.recruit.mapper.CompanyMapper;
import com.mingliang.recruit.model.Company;
import com.mingliang.recruit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company LoginResult(String CompanyId){
        return companyMapper.selectByPrimaryKey(CompanyId);
    }
    @Override
    public void Add(Company company) {
        companyMapper.insert(company);
    }

    @Override
    public Company CompanyInformation(String CompanyId) {
        return companyMapper.selectByPrimaryKey(CompanyId);
    }
//    一个公司对应多个发布职位
    @Override
    public Company CompanyPositions(String CompanyId) {
        return companyMapper.selectpositionById(CompanyId);
    }

    @Override
    public List<Company> SearchCompanyName(String CompanyName) {
        return companyMapper.SearchCompanyName(CompanyName);
    }

}
