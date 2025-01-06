package com.authorization.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authorization.model.User;
import com.authorization.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("MyUserDetailsService: loadUserByUsername");
		User u = userRepository.findByUname(username);
		if (u == null) {
			logger.info("USER = NULL");
			throw new UsernameNotFoundException("user not found!!");
		} else {
			logger.info("USER NOT NULL");
			return new MyUserDetails(u);
		}
	}
}