package com.akpro.service;

import java.util.List;

import com.akpro.bo.JobBo;

public interface JobService {

	public List<JobBo> getAllJobs() throws Exception;

	public JobBo getJobById(Integer jobId) throws Exception;

	public void createOrUpdateJob(JobBo jobBo) throws Exception;

	public void deleteJobById(Integer jobId) throws Exception;

}
