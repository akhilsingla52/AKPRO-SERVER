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
import com.akpro.enums.ResponseStatusEnum;
import com.akpro.service.QuestionCategoryService;

@RestController
@RequestMapping("questionCategory")
public class QuestionCategoryController {
	@Autowired
	private QuestionCategoryService questionCategoryService;
	
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
	
	@RequestMapping(value="/getAllCategoriesWithParams", method=RequestMethod.GET)
	public BaseResponse<?> getAllCategoriesWithParams(@RequestParam(name="page", defaultValue="1") Integer page,
											@RequestParam(name="size", defaultValue="5") Integer size,
											@RequestParam(name="sortorder", defaultValue="ASC") String sortingDirection,
											@RequestParam(name="sortby", defaultValue="id") String sortBy,
											@RequestParam(name="search", defaultValue="") String search) {
		try {
			ListRS<CategoryBo> listRs = questionCategoryService.getAllCategoriesWithParams(page, size, sortingDirection, sortBy, search);
			listRs.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			listRs.setStatusCode(HttpStatus.OK.value());
			listRs.setMessage("Getting Categories");
			
			return listRs;
		} catch(Exception e) {			
			return new BaseResponse<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Categories : "+e.getMessage());
		}
	}
	
	@RequestMapping(value="/{categoryId}", method=RequestMethod.GET)
	public CommonRS<?> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
		try {
			CategoryBo category = questionCategoryService.getCategoryById(categoryId);
			CommonRS<CategoryBo> commonRS = new CommonRS<>();
			commonRS.setStatus(ResponseStatusEnum.SUCCESS.getDescription());
			commonRS.setStatusCode(HttpStatus.OK.value());
			commonRS.setMessage("Getting Category");
			commonRS.setData(category);
			
			return commonRS;
		} catch(Exception e) {			
			return new CommonRS<String>(ResponseStatusEnum.ERROR.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Not get Category : "+e.getMessage());
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
