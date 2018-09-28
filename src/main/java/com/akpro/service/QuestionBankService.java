package com.akpro.service;

import com.akpro.bo.ListRS;
import com.akpro.bo.QuestionBo;

public interface QuestionBankService {

	public ListRS<QuestionBo> getAllQuestions(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception;

	public QuestionBo getQuestionById(Integer questionId) throws Exception;

	public void createOrUpdateQuestion(QuestionBo questionBo) throws Exception;

	public void deleteQuestionById(Integer questionId) throws Exception;

}
