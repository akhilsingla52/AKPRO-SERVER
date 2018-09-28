package com.akpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akpro.bean.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	@Query(value="SELECT u FROM Users u WHERE u.userName LIKE ?1 OR u.email LIKE ?1 OR u.mobile LIKE ?1 OR u.role LIKE ?1")
	Page<Users> findBySearch(String search, Pageable pageable);

}
