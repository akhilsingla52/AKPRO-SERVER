package com.akpro.service;

import static com.akpro.util.Constants.DATE_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akpro.bean.QuestionBank;
import com.akpro.bo.QuestionBo;
import com.akpro.repository.QuestionBankRepository;
import com.akpro.repository.QuestionCategoryRepository;
import com.akpro.util.DateUtils;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {
	
	@Autowired
	private QuestionBankRepository questionBankRepository;

	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;

	public List<QuestionBo> getAllQuestions() throws Exception {
		List<QuestionBo> questionBos = new ArrayList<>();
		
		List<QuestionBank> questions = questionBankRepository.findAll();
		
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
		
		return questionBos;
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
