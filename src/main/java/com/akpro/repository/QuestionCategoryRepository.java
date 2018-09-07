package com.akpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akpro.bean.QuestionCategory;

@Repository
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Integer> {

	@Query(value="SELECT qc FROM QuestionCategory qc WHERE qc.categoryName LIKE ?1")
	public Page<QuestionCategory> findBySearch(String search, Pageable pageable);
	
}
