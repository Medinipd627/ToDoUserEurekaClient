package com.bridgelabz.eureka.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.bridgelabz.eureka.model.User;
import com.bridgelabz.eureka.model.UserLogin;
import com.bridgelabz.eureka.model.UserRegistration;

/*******************************************************************************************
 * Created By:Medini P.D Date:- 11/07/2018 Purpose: User Service class for the
 * login and registration
 ********************************************************************************************/
@RibbonClient(name = "for-service")
public interface UserService {

	public void userRegister(UserRegistration userRegistrationDTO);

	public void userLogin(UserLogin userLogin);

	public void forgotPassword(String email);

	public void resetPassword(UserLogin userLogin);

	public void activate(String token);
	
	List<User> getAllUsers();

}