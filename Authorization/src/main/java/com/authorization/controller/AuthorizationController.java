package com.authorization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.jwt.JwtRequest;
import com.authorization.jwt.JwtUtil;
import com.authorization.model.User;
import com.authorization.service.UserService;

@RestController
@RequestMapping("/users")
public class AuthorizationController {

	@Autowired
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	private static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

	@PostMapping("/getToken")
	public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		logger.info("Checking : createAuthenticationToken");
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		logger.info(userDetails.toString());
		final String jwttoken = jwtTokenUtil.generateToken(userDetails);
		String logCheck = "Received request to generate token for " + authenticationRequest;
		logger.info(logCheck);
		if (jwttoken != null) {
			logger.info("jwtToken is NOT NULL");
			return new ResponseEntity<>(jwttoken, HttpStatus.OK);
		} else {
			logger.info("jwtToken is NULL");
			return new ResponseEntity<>(jwttoken, HttpStatus.BAD_REQUEST);
		}
	}

	private void authenticate(String username, String password) throws UsernameNotFoundException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			logger.info("USER_DISABLED");
			throw new UsernameNotFoundException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			logger.info("INVALID_CREDENTIALS");
			throw new UsernameNotFoundException("INVALID_CREDENTIALS", e);
		}
	}

	@GetMapping("/getuname")
	public ResponseEntity<String> getUname(@RequestHeader("Authorization") String authorization) {
		String token = authorization.substring(7);
		if (token != null) {
			logger.info("Token is NOT NULL : getUname -> AuthorizationController");
			return new ResponseEntity<>(jwtTokenUtil.getUsernameFromToken(token), HttpStatus.OK);
		} else {
			logger.info("Token is NULL : getUname -> AuthorizationController");
			return new ResponseEntity<>(jwtTokenUtil.getUsernameFromToken(token), HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/getUid")
	public ResponseEntity<Integer> getById(@RequestHeader("Authorization") String authorization) {
		String token = authorization.substring(7);
		logger.info(token);
		String uname = jwtTokenUtil.getUsernameFromToken(token);
		User user = userService.findByUname(uname);
		if (user != null) {
			logger.info("User is NOT NULL : getById -> AuthorizationController");
			return new ResponseEntity<>(user.getUid(), HttpStatus.OK);
		} else {
			logger.info("User is NULL : getById -> AuthorizationController");
			return new ResponseEntity<>(0, HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/validate")
	public Boolean validateToken(@RequestHeader("Authorization") String authorization) {
		logger.info("Validating Token -> AuthorizationController");
		return !jwtTokenUtil.isTokenExpired(authorization.substring(7));
	}
}
