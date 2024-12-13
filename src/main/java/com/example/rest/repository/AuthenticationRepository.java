package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.rest.entity.AuthenticationEntity;

@Repository
public interface AuthenticationRepository extends
	JpaRepository<AuthenticationEntity,Long>, JpaSpecificationExecutor<AuthenticationEntity>{

}
