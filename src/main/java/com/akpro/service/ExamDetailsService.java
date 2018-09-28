package com.akpro.service;

import com.akpro.bo.ExamDetailsBo;
import com.akpro.bo.ListRS;

public interface ExamDetailsService {

	public ListRS<ExamDetailsBo> getAllExamDetails(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception;

}
