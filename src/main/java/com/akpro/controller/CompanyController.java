package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.BaseResponse;
import com.akpro.bo.CommonRS;
import com.akpro.bo.CompanyBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public CommonRS<?> getAllCompanies() {
		try {
			List<CompanyBo> companies = companyService.getAllCompanies();
			CommonRS<List<CompanyBo>> commonRS = new CommonRS<>();
			commonRS.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			commonRS.setStatusCode(HttpStatus.OK.value());
			commonRS.setMessage("Getting companies");
			commonRS.setData(companies);
			
			return commonRS;
		} catch(Exception e) {			
			return new CommonRS<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get companies : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/{companyId}", method=RequestMethod.GET)
	public CommonRS<?> getCompanyById(@PathVariable("companyId") Integer companyId) {
		try {
			CompanyBo company = companyService.getCompanyById(companyId);
			CommonRS<CompanyBo> commonRS = new CommonRS<>();
			commonRS.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			commonRS.setStatusCode(HttpStatus.OK.value());
			commonRS.setMessage("Getting company");
			commonRS.setData(company);
			
			return commonRS;
		} catch(Exception e) {			
			return new CommonRS<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get company : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public BaseResponse<?> createCompany(@RequestBody CompanyBo companyBo) {
		try {
			companyService.createOrUpdateCompany(companyBo);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Company Added");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Company Not Added : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public BaseResponse<?> updateCompany(@RequestBody CompanyBo companyBo) {
		try {
			companyService.createOrUpdateCompany(companyBo);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Company Updated");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Company Not Updated : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete/{companyId}", method=RequestMethod.DELETE)
	public BaseResponse<?> deleteCompanyById(@PathVariable("companyId") Integer companyId) {
		try {
			companyService.deleteCompanyById(companyId);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Company Deleted");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not delete company : "+e.getMessage());
		}
	}

}
