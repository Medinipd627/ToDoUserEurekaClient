package com.bridgelabz.eureka.service;

import java.util.List;
/*******************************************************************************************
 * Created By:Medini P.D 
 * Date:- 11/07/2018 
 * Purpose: User Service implementation class for login and registration
 ********************************************************************************************/
import javax.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.eureka.model.User;
import com.bridgelabz.eureka.model.UserLogin;
import com.bridgelabz.eureka.model.UserRegistration;
import com.bridgelabz.eureka.repository.UserRepository;
import com.bridgelabz.eureka.util.JWToken;
import com.bridgelabz.eureka.util.Mailmodel;
import com.bridgelabz.eureka.util.Messages;
import com.bridgelabz.eureka.util.PreConditions;
import com.bridgelabz.eureka.util.Producer;
import com.bridgelabz.eureka.util.UserExceptionHandler;
import io.jsonwebtoken.Claims;

@Service
@PropertySource("classpath:messages.properties")
public class UserServiceImpl implements UserService {

	static Logger logger = LoggerFactory.getLogger(JWToken.class);
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	Mailmodel model;
	@Autowired
	Producer producer;
	@Autowired
	JWToken jwToken;
	@Autowired
	Environment environment;
	@Autowired
	Messages messages;

	/**
	 * @param userRegistrationDTO
	 */
	@Override
	public void userRegister(UserRegistration userRegistrationDTO) {
		try {

			User user = userRepository.findByEmail(userRegistrationDTO.getEmail());
			PreConditions.CheckNotNull(user);
			user = modelMapper.map(userRegistrationDTO, User.class);
			user.setVerified(false);
			user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
			userRepository.save(user);
			String token = jwToken.createJWT(user);
			model.setTo(user.getEmail());
			model.setSubject("Activation Link");
			model.setText(messages.get("101") + token);
			producer.produceMail(model);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new UserExceptionHandler(messages.get("102"));
		}
	}

	/**
	 * @param userLoginDTO
	 */
	@Override
	public void userLogin(UserLogin userLoginDTO) {
		User user = userRepository.findByEmail(userLoginDTO.getEmail());
		PreConditions.CheckNull(user);
		if (user.isVerified()) {
			String password = userLoginDTO.getPassword();
			if (!bCryptPasswordEncoder.matches(password, user.getPassword()))
				throw new UserExceptionHandler(messages.get("109"));
		} else {
			throw new UserExceptionHandler(messages.get("103"));
		}
		jwToken.createJWT(user);
	}

	/**
	 * @param token
	 */
	@Override
	public void activate(String token) {
		Claims email = JWToken.verifyToken(token);
		System.out.println(email);
		User user = userRepository.findByEmail(email.getSubject());
		PreConditions.CheckNull(user);
		user.setVerified(true);
		try {
			userRepository.save(user);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new UserExceptionHandler(messages.get("105"));
		}
	}

	/**
	 * @param email
	 */
	@Override
	public void forgotPassword(String email) {
		User user = userRepository.findByEmail(email);
		PreConditions.CheckNull(user);
		String token = jwToken.createJWT(user);
		model.setSubject(messages.get("106"));
		model.setText(messages.get("103") + token);
		producer.produceMail(model);
	}

	/**
	 * @param userLogin
	 */
	@Override
	public void resetPassword(UserLogin userLogin) {
		User user = userRepository.findByEmail(userLogin.getEmail());
		PreConditions.CheckNull(user);
		user.setPassword(bCryptPasswordEncoder.encode(userLogin.getPassword()));
		try {
			userRepository.save(user);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new UserExceptionHandler(messages.get("108"));
		}
	}
	/**
	 * @return
	 */
	@Override
	public List<User> getAllUsers() {
			List<User> List = userRepository.findAll();
			return List;
		}
}