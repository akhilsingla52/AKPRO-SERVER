package com.akpro.service;

import java.util.List;

import com.akpro.bo.QuestionBo;

public interface QuestionBankService {

	public List<QuestionBo> getAllQuestions() throws Exception;

	public QuestionBo getQuestionById(Integer questionId) throws Exception;

	public void createOrUpdateQuestion(QuestionBo questionBo) throws Exception;

	public void deleteQuestionById(Integer questionId) throws Exception;

}
