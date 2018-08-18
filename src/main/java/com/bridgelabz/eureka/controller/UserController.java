package com.bridgelabz.eureka.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.eureka.model.User;
import com.bridgelabz.eureka.model.UserLogin;
import com.bridgelabz.eureka.model.UserRegistration;
import com.bridgelabz.eureka.service.UserServiceImpl;
import com.bridgelabz.eureka.util.Response;

/**********************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/08/2018
 * Purpose:User controller class for the login and registration
 ***********************************************************************************************/
@RestController
@RequestMapping(value="/user")
public class UserController {
	static Logger logger= LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserServiceImpl userService;
/*	
 @Autowired
RedisRepository  redisRepository;*/

	/**
	 * @param userRegistrationDTO
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    private ResponseEntity<Response> register(
  @RequestBody UserRegistration userRegistrationDTO) {
   userService.userRegister(userRegistrationDTO);
   return new ResponseEntity<>(new Response("Registration Success", HttpStatus.CREATED), HttpStatus.OK);
	}
	
	/**
	 * @param userLogin
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	   public ResponseEntity<Response> login(@RequestBody UserLogin userLogin) {
		userService.userLogin(userLogin);
		return new ResponseEntity<>(new Response("Login Success", HttpStatus.OK), HttpStatus.OK);
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	    public ResponseEntity<Response> activation(HttpServletRequest httpServletRequest) {
		String token=httpServletRequest.getHeader("Token");
		userService.activate(token);
	//redisRepository.saveInRedis(token);
		return new ResponseEntity<>(new Response("Account Activated", HttpStatus.OK), HttpStatus.OK);
	}

	/**
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/forgotpassword/{email:.+}", method = RequestMethod.GET)
	   public ResponseEntity<Response> forgetpassword(@PathVariable String email) {
		userService.forgotPassword(email);
		return new ResponseEntity<>(new Response("Password Reset Link Sent", HttpStatus.OK), HttpStatus.OK);
	}
	
	/**
	 * @param userLogin
	 * @return
	 */
	@RequestMapping(value = "/reset-password/{token}", method = RequestMethod.GET)
	    public ResponseEntity<Response> newPassword(@RequestBody UserLogin userLogin ) {
		userService.resetPassword(userLogin);
		return new ResponseEntity<>(new Response("Password Changed Succesfully", HttpStatus.OK), HttpStatus.OK);
	}
	
	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<?> getAllUsers() {
		List<	User> list = userService.getAllUsers();
	return list;
}
}