package com.akpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akpro.bean.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

}
