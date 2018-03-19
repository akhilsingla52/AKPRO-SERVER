package com.akpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akpro.bean.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
