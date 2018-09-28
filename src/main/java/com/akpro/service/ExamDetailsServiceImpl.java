package com.akpro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.akpro.bean.ExamDetails;
import com.akpro.bo.ExamDetailsBo;
import com.akpro.bo.ListRS;
import com.akpro.repository.ExamDetailsRepository;
import com.akpro.util.Constants;
import com.akpro.util.DateUtils;

@Service
public class ExamDetailsServiceImpl implements ExamDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExamDetailsServiceImpl.class);
	
	@Autowired
	private ExamDetailsRepository examDetailsRepository;
	
	public ListRS<ExamDetailsBo> getAllExamDetails(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception {
		List<ExamDetailsBo> examDetailsBos = new ArrayList<>();
		search = "%"+new String(Hex.decodeHex(search), "UTF-8")+"%";
		LOGGER.info("Search: "+search);
		
		Direction direction;
		if (sortingDirection.equals("ASC")) {
			direction = Sort.Direction.ASC;
		} else {
			direction = Sort.Direction.DESC;
		}
		
		Page<ExamDetails> exams = examDetailsRepository.findBySearch(search, PageRequest.of(page-1, size, direction, sortBy));
		
		for(ExamDetails exam:exams) {
			ExamDetailsBo examDetail = new ExamDetailsBo();
			examDetail.setId(exam.getId());
			examDetail.setName( (exam.getUser()!=null && exam.getUser().getPersonalDetails()!=null) ? exam.getUser().getPersonalDetails().getName() : StringUtils.EMPTY );
			examDetail.setEmail( (exam.getUser()!=null) ? exam.getUser().getEmail() : StringUtils.EMPTY );
			examDetail.setAttempt(exam.getAttempt());
			examDetail.setRight(exam.getRight());
			examDetail.setMarks(exam.getMarks());
			examDetail.setDate(DateUtils.getUTCDate(exam.getTimeCreated(), Constants.DATE_FORMAT));
			
			examDetailsBos.add(examDetail);
		}
		ListRS<ExamDetailsBo> listRs = new ListRS<>();
		listRs.setData(examDetailsBos);
		listRs.setCount(exams.getTotalElements());
		listRs.setPageCount(exams.getTotalPages());
		
		return listRs;
	}

}
