package com.akpro.service;

import java.util.List;

import com.akpro.bo.JobBo;

public interface JobService {

	public List<JobBo> getAllJobs() throws Exception;

}
