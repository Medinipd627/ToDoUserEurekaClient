package com.bridgelabz.eureka.repository;

import java.util.List;
import java.util.Optional;
/****************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose: User repository class for the login and registration
 *******************************************************************************************************/
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.eureka.model.User;

import io.jsonwebtoken.Claims;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	public User findByEmail(String email);

	public User findByEmail(Claims email);

	public Optional<User> findById(Claims userId);

}