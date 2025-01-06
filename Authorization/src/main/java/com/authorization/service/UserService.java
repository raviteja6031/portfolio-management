package com.authorization.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authorization.model.User;
import com.authorization.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@PostConstruct
	public void initDB() {
		List<User> users = new ArrayList<>();
		users.add(new User("Shreya", "shreya"));
		users.add(new User("Raghav", "raghav"));
		users.add(new User("Samaira", "samaira"));
		userRepository.saveAll(users);
	}

	public User findByUname(String uname) {
		String logCheck = "UserService.class -> findByUname" + uname;
		logger.info(logCheck);
		return userRepository.findByUname(uname);
	}
	
	public User login(String uname, String passwd) {
		try{
			logger.info("UserService -> login(String uname, String passwd)");
			return userRepository.findByUnameAndPasswd(uname, passwd);
		}
		catch(EntityNotFoundException e) {
			logger.info("USER NOT FOUND");
			return null;
		}
	}
}
