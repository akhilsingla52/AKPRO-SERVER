package com.akpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akpro.bean.Placement;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer> {

}
