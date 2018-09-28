package com.akpro.service;

import static com.akpro.util.Constants.DATE_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.akpro.bean.QuestionBank;
import com.akpro.bo.ListRS;
import com.akpro.bo.QuestionBo;
import com.akpro.repository.QuestionBankRepository;
import com.akpro.repository.QuestionCategoryRepository;
import com.akpro.util.DateUtils;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionBankServiceImpl.class);
	
	@Autowired
	private QuestionBankRepository questionBankRepository;

	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;
	
	public ListRS<QuestionBo> getAllQuestions(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception {
		List<QuestionBo> questionBos = new ArrayList<>();
		search = "%"+new String(Hex.decodeHex(search), "UTF-8")+"%";
		LOGGER.info("Search: "+search);
		
		Direction direction;
		if (sortingDirection.equals("ASC")) {
			direction = Sort.Direction.ASC;
		} else {
			direction = Sort.Direction.DESC;
		}
		
		Page<QuestionBank> questions = questionBankRepository.findBySearch(search, PageRequest.of(page-1, size, direction, sortBy));
		
		for(QuestionBank question: questions) {
			QuestionBo questionBo = new QuestionBo();
			questionBo.setId(question.getId());
			questionBo.setCategoryId(question.getCategory().getId());
			questionBo.setCategoryName(question.getCategory().getCategoryName());
			questionBo.setQuestion(question.getQuestion());
			questionBo.setOptions( Arrays.asList(StringUtils.split(question.getOptions(), ", ")) );
			questionBo.setAnswer(question.getAnswer());
			questionBo.setCreatedDate(DateUtils.getUTCDate(question.getTimeCreated(), DATE_FORMAT));
			questionBo.setModifiedDate(DateUtils.getUTCDate(question.getTimeCreated(), DATE_FORMAT));
			
			questionBos.add(questionBo);
		}
		
		ListRS<QuestionBo> listRs = new ListRS<>();
		listRs.setData(questionBos);
		listRs.setCount(questions.getTotalElements());
		listRs.setPageCount(questions.getTotalPages());
		
		return listRs;
	}

	public QuestionBo getQuestionById(Integer questionId) throws Exception {
		if(questionId==null || questionId==0)
			throw new Exception("Id is null or 0");
		QuestionBo questionBo = null;
		QuestionBank question = questionBankRepository.findById(questionId).get();
		
		if(question!=null) {
			questionBo = new QuestionBo();
			questionBo.setId(question.getId());
			questionBo.setCategoryId(question.getCategory().getId());
			questionBo.setCategoryName(question.getCategory().getCategoryName());
			questionBo.setQuestion(question.getQuestion());
			questionBo.setOptions( Arrays.asList(StringUtils.split(question.getOptions(), ", ")) );
			questionBo.setAnswer(question.getAnswer());
			questionBo.setCreatedDate(DateUtils.getUTCDate(question.getTimeCreated(), DATE_FORMAT));
			questionBo.setModifiedDate(DateUtils.getUTCDate(question.getTimeCreated(), DATE_FORMAT));
		}
		
		return questionBo;
	}

	public void createOrUpdateQuestion(QuestionBo questionBo) throws Exception {
		if(questionBo==null)
			throw new Exception("Send Object null");
		
		QuestionBank question = new QuestionBank();
		if(questionBo.getId()!=null && questionBo.getId()!=0)
			question = questionBankRepository.findById(questionBo.getId()).get();
		question.setCategory( questionCategoryRepository.findById(questionBo.getCategoryId()).get() );
		question.setQuestion(questionBo.getQuestion());
		question.setOptions( StringUtils.join(questionBo.getOptions(), ", ") );
		question.setAnswer(questionBo.getAnswer());
		
		questionBankRepository.save(question);
	}

	public void deleteQuestionById(Integer questionId) throws Exception {
		questionBankRepository.deleteById(questionId);
	}

}
