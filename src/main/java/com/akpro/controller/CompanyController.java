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
import com.akpro.bo.CompanyBo;
import com.akpro.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value="/allCompanies", method=RequestMethod.GET)
	public BaseResponse<?> getAllCompanies() {
		try {
			List<CompanyBo> companies = companyService.getAllCompanies();
			
			return new BaseResponse<List<CompanyBo>>(companies, "SUCCESS", HttpStatus.OK.value(), "Getting companies");
		} catch(Exception e) {			
			return new BaseResponse<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get companies : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/getCompanyById/{companyId}", method=RequestMethod.GET)
	public BaseResponse<?> getCompanyById(@PathVariable("companyId") Integer companyId) {
		try {
			CompanyBo company = companyService.getCompanyById(companyId);
			
			return new BaseResponse<CompanyBo>(company, "SUCCESS", HttpStatus.OK.value(), "Getting company");
		} catch(Exception e) {			
			return new BaseResponse<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get company : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/createCompany", method=RequestMethod.POST)
	public BaseResponse<?> createCompany(@RequestBody CompanyBo companyBo) {
		try {
			companyService.createOrUpdateCompany(companyBo);
			
			return new BaseResponse<String>("SUCCESS", HttpStatus.OK.value(), "Company Added");
		} catch(Exception e) {			
			return new BaseResponse<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Company Not Added : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/updateCompany", method=RequestMethod.PUT)
	public BaseResponse<?> updateCompany(@RequestBody CompanyBo companyBo) {
		try {
			companyService.createOrUpdateCompany(companyBo);
			
			return new BaseResponse<String>("SUCCESS", HttpStatus.OK.value(), "Company Added");
		} catch(Exception e) {			
			return new BaseResponse<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Company Not Added : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/deleteCompanyById/{companyId}", method=RequestMethod.DELETE)
	public BaseResponse<?> deleteCompanyById(@PathVariable("companyId") Integer companyId) {
		try {
			companyService.deleteCompanyById(companyId);
			
			return new BaseResponse<String>("SUCCESS", HttpStatus.OK.value(), "Company Deleted");
		} catch(Exception e) {			
			return new BaseResponse<String>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not delete company : "+e.getMessage());
		}
	}

}
