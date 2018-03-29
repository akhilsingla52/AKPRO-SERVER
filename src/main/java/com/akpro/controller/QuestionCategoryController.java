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
import com.akpro.bo.CategoryBo;
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.QuestionCategoryService;

@RestController
@RequestMapping("questionCategory")
public class QuestionCategoryController {
	@Autowired
	private QuestionCategoryService questionCategoryService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public BaseResponse<?> getAllCategories() {
		try {
			List<CategoryBo> categories = questionCategoryService.getAllCategories();
			
			return new BaseResponse<List<CategoryBo>>(categories, ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Getting Categories");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Categories : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/{categoryId}", method=RequestMethod.GET)
	public BaseResponse<?> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
		try {
			CategoryBo category = questionCategoryService.getCategoryById(categoryId);
			
			return new BaseResponse<CategoryBo>(category, ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Getting Category");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Category : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public BaseResponse<?> createCategory(@RequestBody CategoryBo categoryBo) {
		try {
			questionCategoryService.createOrUpdateCategory(categoryBo);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Category Added");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Category Not Added : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public BaseResponse<?> updateCategory(@RequestBody CategoryBo categoryBo) {
		try {
			questionCategoryService.createOrUpdateCategory(categoryBo);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Category Updated");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Category Not Updated : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/delete/{categoryId}", method=RequestMethod.DELETE)
	public BaseResponse<?> deleteCategoryById(@PathVariable("categoryId") Integer categoryId) {
		try {
			questionCategoryService.deleteCategoryById(categoryId);
			
			return new BaseResponse<String>(ResponseStatusEnum.SUCCESS.getDescription(), HttpStatus.OK.value(), "Category Deleted");
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not delete Category : "+e.getMessage());
		}
	}
}
