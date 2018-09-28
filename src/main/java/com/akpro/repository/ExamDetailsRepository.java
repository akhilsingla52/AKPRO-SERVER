package com.akpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akpro.bean.ExamDetails;

@Repository
public interface ExamDetailsRepository extends JpaRepository<ExamDetails, Integer> {

	@Query(value="SELECT ed FROM ExamDetails ed WHERE ed.user.personalDetails.name LIKE ?1 OR ed.user.email LIKE ?1 OR ed.marks LIKE ?1")
	Page<ExamDetails> findBySearch(String search, Pageable pageable);

}
