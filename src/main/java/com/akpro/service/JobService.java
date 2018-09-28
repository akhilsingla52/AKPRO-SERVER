package com.akpro.service;

import com.akpro.bo.JobBo;
import com.akpro.bo.ListRS;

public interface JobService {

	public ListRS<JobBo> getAllJobs(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception;

	public JobBo getJobById(Integer jobId) throws Exception;

	public void createOrUpdateJob(JobBo jobBo) throws Exception;

	public void deleteJobById(Integer jobId) throws Exception;

}
