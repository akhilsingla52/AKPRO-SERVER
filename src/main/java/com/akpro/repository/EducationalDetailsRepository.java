package com.akpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akpro.bean.EducationalDetails;

@Repository
public interface EducationalDetailsRepository extends JpaRepository<EducationalDetails, Integer> {

}
