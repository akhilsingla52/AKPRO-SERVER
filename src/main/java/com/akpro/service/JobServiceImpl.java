package com.akpro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akpro.bean.Job;
import com.akpro.bo.JobBo;
import com.akpro.repository.CompanyRepository;
import com.akpro.repository.JobRepository;
import com.akpro.util.Constants;
import com.akpro.util.DateUtils;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

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
			jobBo.setExperience(job.getExperience());
			jobBo.setSalary(job.getSalary());
			jobBo.setCreatedDate(DateUtils.getUTCDate(job.getTimeCreated(), Constants.DATE_FORMAT));
			jobBo.setModifiedDate(DateUtils.getUTCDate(job.getTimeModified(), Constants.DATE_FORMAT));
			
			jobBos.add(jobBo);
		}
		
		return jobBos;
	}

	
	public JobBo getJobById(Integer jobId) throws Exception {
		Optional<Job> optionalJob = jobRepository.findById(jobId);
		Job job = optionalJob.get();
		
		if(job!=null) {
			JobBo jobBo = new JobBo();
			jobBo.setId(job.getId());
			jobBo.setCompanyId(job.getCompany().getId());
			jobBo.setCompanyName(job.getCompany().getCompanyName());
			jobBo.setLocation(job.getLocation());
			jobBo.setTechnology(job.getTechnology());
			jobBo.setRole(job.getRole());
			jobBo.setExperience(job.getExperience());
			jobBo.setSalary(job.getSalary());
			jobBo.setCreatedDate(DateUtils.getUTCDate(job.getTimeCreated(), Constants.DATE_FORMAT));
			jobBo.setModifiedDate(DateUtils.getUTCDate(job.getTimeModified(), Constants.DATE_FORMAT));
			return jobBo;
		}
		
		return null;
	}

	public void createOrUpdateJob(JobBo jobBo) throws Exception {
		if(jobBo==null)
			throw new Exception("Send Object null");
		
		Job job = new Job();
		if(jobBo.getId()!=null && jobBo.getId()!=0)
			job = jobRepository.findById(jobBo.getId()).get();
		job.setCompany( companyRepository.findById(jobBo.getCompanyId()).get() );
		job.setLocation(jobBo.getLocation());
		job.setTechnology(jobBo.getTechnology());
		job.setRole(jobBo.getRole());
		job.setExperience(jobBo.getExperience());
		job.setSalary(jobBo.getSalary());
		
		jobRepository.save(job);
	}

	public void deleteJobById(Integer jobId) throws Exception {
		jobRepository.deleteById(jobId);
	}

}
