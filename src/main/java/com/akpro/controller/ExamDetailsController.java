package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.BaseResponse;
import com.akpro.bo.ExamDetailsBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.ExamDetailsService;

@RestController
@RequestMapping("exam")
public class ExamDetailsController {
	
	@Autowired
	private ExamDetailsService examDetailsService;
	
	@RequestMapping(value="/examDetails", method=RequestMethod.GET)
	public BaseResponse<?> getExamDetails() {
		try {
			List<ExamDetailsBo> examDetails = examDetailsService.getExamDetails();
			
			return new BaseResponse<List<ExamDetailsBo>>(examDetails, ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Getting exam details");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get exam details : "+e.getMessage());
		}
	}

}
