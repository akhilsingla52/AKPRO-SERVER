package com.akpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akpro.bean.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
