package com.akpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akpro.bean.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

	@Query(value="SELECT j FROM Job j WHERE j.company.companyName LIKE ?1 OR j.location LIKE ?1 OR j.technology LIKE ?1 OR j.role LIKE ?1")
	Page<Job> findBySearch(String search, Pageable pageable);

}
