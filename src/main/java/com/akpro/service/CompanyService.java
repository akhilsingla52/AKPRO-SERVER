package com.akpro.service;

import java.util.List;

import com.akpro.bo.CompanyBo;

public interface CompanyService {

	public List<CompanyBo> getAllCompanies() throws Exception;

	public CompanyBo getCompanyById(Integer companyId) throws Exception;

	public void deleteCompanyById(Integer companyId) throws Exception;

	public void createOrUpdateCompany(CompanyBo companyBo) throws Exception;

}
