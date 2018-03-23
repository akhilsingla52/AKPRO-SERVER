package com.akpro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akpro.bean.ExamDetails;
import com.akpro.bo.ExamDetailsBo;
import com.akpro.repository.ExamDetailsRepository;
import com.akpro.util.Constants;
import com.akpro.util.DateUtils;

@Service
public class ExamDetailsServiceImpl implements ExamDetailsService {
	@Autowired
	private ExamDetailsRepository examDetailsRepository;
	
	public List<ExamDetailsBo> getExamDetails() throws Exception {
		List<ExamDetailsBo> examDetails = new ArrayList<>();
		
		List<ExamDetails> exams = examDetailsRepository.findAll();
		
		for(ExamDetails exam:exams) {
			ExamDetailsBo examDetail = new ExamDetailsBo();
			examDetail.setId(exam.getId());
			examDetail.setName( (exam.getUser()!=null && exam.getUser().getPersonalDetails()!=null) ? exam.getUser().getPersonalDetails().getName() : StringUtils.EMPTY );
			examDetail.setEmail( (exam.getUser()!=null) ? exam.getUser().getEmail() : StringUtils.EMPTY );
			examDetail.setAttempt(exam.getAttempt());
			examDetail.setRight(exam.getRight());
			examDetail.setMarks(exam.getMarks());
			examDetail.setDate(DateUtils.getUTCDate(exam.getTimeCreated(), Constants.DATE_FORMAT));
			
			examDetails.add(examDetail);
		}
		
		return examDetails;
	}

}
