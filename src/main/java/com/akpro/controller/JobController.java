package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
