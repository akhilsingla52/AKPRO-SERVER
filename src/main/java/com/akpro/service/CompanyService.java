package com.akpro.service;

import java.util.List;

import com.akpro.bo.CompanyBo;
import com.akpro.bo.ListRS;

public interface CompanyService {

	public ListRS<CompanyBo> getAllCompanies(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception;

	public List<CompanyBo> getAllCompanies() throws Exception;

	public CompanyBo getCompanyById(Integer companyId) throws Exception;

	public void deleteCompanyById(Integer companyId) throws Exception;

	public void createOrUpdateCompany(CompanyBo companyBo) throws Exception;

}
