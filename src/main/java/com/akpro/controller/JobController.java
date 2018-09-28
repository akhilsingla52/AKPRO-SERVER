package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.BaseResponse;
import com.akpro.bo.CommonRS;
import com.akpro.bo.CompanyBo;
import com.akpro.bo.JobBo;
import com.akpro.bo.ListRS;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.CompanyService;
import com.akpro.service.JobService;

@RestController
@RequestMapping("job")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value="/getAllJobs", method=RequestMethod.GET)
	public BaseResponse<?> getAllJobs(@RequestParam(name="page", defaultValue="1") Integer page,
									@RequestParam(name="size", defaultValue="5") Integer size,
									@RequestParam(name="sortorder", defaultValue="ASC") String sortingDirection,
									@RequestParam(name="sortby", defaultValue="id") String sortBy,
									@RequestParam(name="search", defaultValue="") String search) {
		try {
			ListRS<JobBo> listRs = jobService.getAllJobs(page, size, sortingDirection, sortBy, search);
			listRs.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			listRs.setStatusCode(HttpStatus.OK.value());
			listRs.setMessage("Getting Jobs");
			
			return listRs;
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Jobs : "+e.getMessage());
		}
	}

	@RequestMapping(value="/getAllCompanies", method=RequestMethod.GET)
	public BaseResponse<?> getAllCompanies() {
		try {
			List<CompanyBo> companyBos = companyService.getAllCompanies();
			
			ListRS<CompanyBo> listRs = new ListRS<>();
			listRs.setData(companyBos);
			listRs.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			listRs.setStatusCode(HttpStatus.OK.value());
			listRs.setMessage("Getting companies");
			
			return listRs;
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get companies : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/{jobId}", method=RequestMethod.GET)
	public CommonRS<?> getJobById(@PathVariable("jobId") Integer jobId) {
		try {
			JobBo jobBo = jobService.getJobById(jobId);
			CommonRS<JobBo> commonRS = new CommonRS<>();
			commonRS.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			commonRS.setStatusCode(HttpStatus.OK.value());
			commonRS.setMessage("Getting Job");
			commonRS.setData(jobBo);
			
			
			return commonRS;
		} catch(Exception e) {			
			return new CommonRS<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Job : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public BaseResponse<?> createJob(@RequestBody JobBo jobBo) {
		try {
			jobService.createOrUpdateJob(jobBo);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Job Added");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Job Not Added : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public BaseResponse<?> updateJob(@RequestBody JobBo jobBo) {
		try {
			jobService.createOrUpdateJob(jobBo);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Job Added");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Job Not Added : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete/{jobId}", method=RequestMethod.DELETE)
	public BaseResponse<?> deleteJobById(@PathVariable("jobId") Integer jobId) {
		try {
			jobService.deleteJobById(jobId);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Job Deleted");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not delete Job : "+e.getMessage());
		}
	}
}
