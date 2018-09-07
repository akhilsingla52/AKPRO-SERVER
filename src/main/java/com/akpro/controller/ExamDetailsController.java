package com.akpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akpro.bo.CommonRS;
import com.akpro.bo.ExamDetailsBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.ExamDetailsService;

@RestController
@RequestMapping("exam")
public class ExamDetailsController {
	
	@Autowired
	private ExamDetailsService examDetailsService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public CommonRS<?> getExamDetails() {
		try {
			List<ExamDetailsBo> examDetails = examDetailsService.getExamDetails();
			CommonRS<List<ExamDetailsBo>> commonRS = new CommonRS<>();
			commonRS.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			commonRS.setStatusCode(HttpStatus.OK.value());
			commonRS.setMessage("Getting exam details");
			commonRS.setData(examDetails);
			
			return commonRS;
		} catch(Exception e) {			
			return new CommonRS<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get exam details : "+e.getMessage());
		}
	}

}
