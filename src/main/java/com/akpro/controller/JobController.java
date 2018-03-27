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
import com.akpro.bo.JobBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.JobService;

@RestController
@RequestMapping("job")
public class JobController {

	
	@Autowired
	private JobService jobService;
	
	@RequestMapping(value="/allJobs", method=RequestMethod.GET)
	public BaseResponse<?> getAllJobss() {
		try {
			List<JobBo> jobs = jobService.getAllJobs();
			
			return new BaseResponse<List<JobBo>>(jobs, ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Getting Jobs");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Jobs : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/{jobId}", method=RequestMethod.GET)
	public BaseResponse<?> getJobById(@PathVariable("jobId") Integer jobId) {
		try {
			JobBo jobBo = jobService.getJobById(jobId);
			
			return new BaseResponse<JobBo>(jobBo, ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Getting Job");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Job : "+e.getMessage());
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
