package com.akpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akpro.bean.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query(value="SELECT c FROM Company c WHERE c.imageUrl LIKE ?1 OR c.companyName LIKE ?1 OR c.website LIKE ?1")
	Page<Company> findBySearch(String search, Pageable pageable);
	
	
}
