package com.akpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akpro.bean.QuestionBank;

@Repository
public interface QuestionBankRepository extends JpaRepository<QuestionBank, Integer> {

	@Query(value="SELECT q FROM QuestionBank q WHERE q.category.categoryName LIKE ?1 OR q.question LIKE ?1")
	Page<QuestionBank> findBySearch(String search, Pageable pageable);

}
