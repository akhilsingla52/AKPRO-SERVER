package com.akpro.service;

import java.util.List;

import com.akpro.bo.CategoryBo;

public interface QuestionCategoryService {

	public List<CategoryBo> getAllCategories() throws Exception;

	public CategoryBo getCategoryById(Integer categoryId) throws Exception;

	public void createOrUpdateCategory(CategoryBo categoryBo) throws Exception;

	public void deleteCategoryById(Integer categoryId) throws Exception;

}
