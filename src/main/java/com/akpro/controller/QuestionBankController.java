package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.BaseResponse;
import com.akpro.bo.CommonRS;
import com.akpro.bo.QuestionBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.QuestionBankService;

@RestController
@RequestMapping("questionBank")
public class QuestionBankController {
	
	@Autowired
	private QuestionBankService questionBankService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public CommonRS<?> getAllQuestions() {
		try {
			List<QuestionBo> questions = questionBankService.getAllQuestions();
			CommonRS<List<QuestionBo>> commonRS = new CommonRS<>();
			commonRS.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			commonRS.setStatusCode(HttpStatus.OK.value());
			commonRS.setMessage("Getting questions");
			commonRS.setData(questions);
			
			return commonRS;
		} catch(Exception e) {			
			return new CommonRS<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get questions : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/{questionId}", method=RequestMethod.GET)
	public CommonRS<?> getQuestionById(@PathVariable("questionId") Integer questionId) {
		try {
			QuestionBo question = questionBankService.getQuestionById(questionId);
			CommonRS<QuestionBo> commonRS = new CommonRS<>();
			commonRS.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			commonRS.setStatusCode(HttpStatus.OK.value());
			commonRS.setMessage("Getting question");
			commonRS.setData(question);
			
			return commonRS;
		} catch(Exception e) {			
			return new CommonRS<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get question : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public BaseResponse<?> createQuestion(@RequestBody QuestionBo questionBo) {
		try {
			questionBankService.createOrUpdateQuestion(questionBo);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Question Added");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Question Not Added : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public BaseResponse<?> updateQuestion(@RequestBody QuestionBo questionBo) {
		try {
			questionBankService.createOrUpdateQuestion(questionBo);
		
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Question Updated");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Question Not Updated : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete/{questionId}", method=RequestMethod.DELETE)
	public BaseResponse<?> deleteQuestionById(@PathVariable("questionId") Integer questionId) {
		try {
			questionBankService.deleteQuestionById(questionId);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Question Deleted");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not delete Question : "+e.getMessage());
		}
	}

}
