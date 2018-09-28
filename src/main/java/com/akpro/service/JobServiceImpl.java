package com.akpro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.akpro.bean.Job;
import com.akpro.bo.JobBo;
import com.akpro.bo.ListRS;
import com.akpro.repository.CompanyRepository;
import com.akpro.repository.JobRepository;
import com.akpro.util.Constants;
import com.akpro.util.DateUtils;

@Service
public class JobServiceImpl implements JobService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public ListRS<JobBo> getAllJobs(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception {
		List<JobBo> jobBos = new ArrayList<>();
		search = "%"+new String(Hex.decodeHex(search), "UTF-8")+"%";
		LOGGER.info("Search: "+search);
		
		Direction direction;
		if (sortingDirection.equals("ASC")) {
			direction = Sort.Direction.ASC;
		} else {
			direction = Sort.Direction.DESC;
		}
		
		Page<Job> jobs = jobRepository.findBySearch(search, PageRequest.of(page-1, size, direction, sortBy));
		
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
		
		ListRS<JobBo> listRs = new ListRS<>();
		listRs.setData(jobBos);
		listRs.setCount(jobs.getTotalElements());
		listRs.setPageCount(jobs.getTotalPages());
		
		return listRs;
	}
	
	public JobBo getJobById(Integer jobId) throws Exception {
		if(jobId==null || jobId==0)
			throw new Exception("Id is null or 0");
		JobBo jobBo = null;
		Job job = jobRepository.findById(jobId).get();
		
		if(job!=null) {
			jobBo = new JobBo();
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
		}

		return jobBo;
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
