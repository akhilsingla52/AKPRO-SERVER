package com.akpro.service;

import static com.akpro.util.Constants.DATE_FORMAT;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akpro.bean.QuestionCategory;
import com.akpro.bo.CategoryBo;
import com.akpro.repository.QuestionCategoryRepository;
import com.akpro.util.DateUtils;

@Service
public class QuestionCategoryServiceImpl implements QuestionCategoryService {
	@Autowired
	private QuestionCategoryRepository questionCategoryRepository;
	
	public List<CategoryBo> getAllCategories() throws Exception {
		List<CategoryBo> categoryBos = new ArrayList<>();
		List<QuestionCategory> categories = questionCategoryRepository.findAll();
		
		for(QuestionCategory category: categories) {
			CategoryBo categoryBo = new CategoryBo();
			categoryBo.setId(category.getId());
			categoryBo.setCategoryName(category.getCategoryName());
			categoryBo.setCreatedDate(DateUtils.getUTCDate(category.getTimeCreated(), DATE_FORMAT));
			categoryBo.setModifiedDate(DateUtils.getUTCDate(category.getTimeModified(), DATE_FORMAT));
			
			categoryBos.add(categoryBo);
		}
		
		return categoryBos;
	}
	
	public CategoryBo getCategoryById(Integer categoryId) throws Exception {
		if(categoryId==null || categoryId==0)
			throw new Exception("Id is null or 0");
		CategoryBo categoryBo = null;
		QuestionCategory category = questionCategoryRepository.findById(categoryId).get();
		
		if(category!=null) {
			categoryBo = new CategoryBo();
			categoryBo.setId(category.getId());
			categoryBo.setCategoryName(category.getCategoryName());
			categoryBo.setCreatedDate(DateUtils.getUTCDate(category.getTimeCreated(), DATE_FORMAT));
			categoryBo.setModifiedDate(DateUtils.getUTCDate(category.getTimeModified(), DATE_FORMAT));
		}
		
		return categoryBo;
	}
	
	public void createOrUpdateCategory(CategoryBo categoryBo) throws Exception {
		if(categoryBo==null)
			throw new Exception("Send Object null");
		
		QuestionCategory category = new QuestionCategory();
		if(categoryBo.getId()!=null && categoryBo.getId()!=0)
			category = questionCategoryRepository.findById(categoryBo.getId()).get();
		category.setCategoryName(categoryBo.getCategoryName());
		
		questionCategoryRepository.save(category);
	}

	public void deleteCategoryById(Integer categoryId) throws Exception {
		questionCategoryRepository.deleteById(categoryId);
	}
}
