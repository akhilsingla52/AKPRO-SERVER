package com.akpro.service;

import java.util.List;

import com.akpro.bo.CategoryBo;
import com.akpro.bo.ListRS;

public interface QuestionCategoryService {

	public List<CategoryBo> getAllCategories() throws Exception;

	public ListRS<CategoryBo> getAllCategoriesWithParams(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception;

	public CategoryBo getCategoryById(Integer categoryId) throws Exception;

	public void createOrUpdateCategory(CategoryBo categoryBo) throws Exception;

	public void deleteCategoryById(Integer categoryId) throws Exception;

}
