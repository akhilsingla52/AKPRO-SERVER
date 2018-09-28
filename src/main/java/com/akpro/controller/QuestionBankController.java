package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.BaseResponse;
import com.akpro.bo.CategoryBo;
import com.akpro.bo.CommonRS;
import com.akpro.bo.ListRS;
import com.akpro.bo.QuestionBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.QuestionBankService;
import com.akpro.service.QuestionCategoryService;

@RestController
@RequestMapping("questionBank")
public class QuestionBankController {
	
	@Autowired
	private QuestionBankService questionBankService;
	
	@Autowired
	private QuestionCategoryService questionCategoryService;
	
	@RequestMapping(value="/getAllQuestions", method=RequestMethod.GET)
	public BaseResponse<?> getAllQuestions(@RequestParam(name="page", defaultValue="1") Integer page,
									@RequestParam(name="size", defaultValue="5") Integer size,
									@RequestParam(name="sortorder", defaultValue="ASC") String sortingDirection,
									@RequestParam(name="sortby", defaultValue="id") String sortBy,
									@RequestParam(name="search", defaultValue="") String search) {
		try {
			ListRS<QuestionBo> listRs = questionBankService.getAllQuestions(page, size, sortingDirection, sortBy, search);
			listRs.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			listRs.setStatusCode(HttpStatus.OK.value());
			listRs.setMessage("Getting questions");
			
			return listRs;
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get questions : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/getAllCategories", method=RequestMethod.GET)
	public BaseResponse<?> getAllCategories() {
		try {
			List<CategoryBo> categoryBos = questionCategoryService.getAllCategories(); 
			ListRS<CategoryBo> listRs = new ListRS<>();
			listRs.setData(categoryBos);
			listRs.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			listRs.setStatusCode(HttpStatus.OK.value());
			listRs.setMessage("Getting Categories for questions");
			
			return listRs;
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Categories : "+e.getMessage());
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
