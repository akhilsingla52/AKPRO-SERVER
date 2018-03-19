package com.akpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akpro.bean.QuestionBank;

@Repository
public interface QuestionBankRepository extends JpaRepository<QuestionBank, Integer> {

}
