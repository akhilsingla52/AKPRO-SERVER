package com.akpro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akpro.bean.Job;
import com.akpro.bo.JobBo;
import com.akpro.repository.JobRepository;
import com.akpro.util.Constants;
import com.akpro.util.DateUtils;

@Service
public class JobServiceImpl implements JobService {
	@Autowired
	private JobRepository jobRepository;

	@Override
	public List<JobBo> getAllJobs() throws Exception {
		List<JobBo> jobBos = new ArrayList<>();
		
		List<Job> jobs = jobRepository.findAll();
		
		for(Job job: jobs) {
			JobBo jobBo = new JobBo();
			jobBo.setId(job.getId());
			jobBo.setCompanyId(job.getCompany().getId());
			jobBo.setCompanyName(job.getCompany().getCompanyName());
			jobBo.setLocation(job.getLocation());
			jobBo.setTechnology(job.getTechnology());
			jobBo.setRole(job.getRole());
			jobBo.setSalary(job.getSalary());
			jobBo.setCreatedDate(DateUtils.getUTCDate(job.getTimeCreated(), Constants.DATE_FORMAT));
			jobBo.setModifiedDate(DateUtils.getUTCDate(job.getTimeModified(), Constants.DATE_FORMAT));
			
			jobBos.add(jobBo);
		}
		
		return jobBos;
	}

}
