package com.akpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akpro.bean.ExamDetails;

@Repository
public interface ExamDetailsRepository extends JpaRepository<ExamDetails, Integer> {

}
