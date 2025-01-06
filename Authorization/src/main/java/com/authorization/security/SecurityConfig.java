package com.authorization.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authorization.controller.AuthorizationController;
import com.authorization.jwt.JwtRequestFilter;
import com.authorization.service.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	UserDetailsService myUserDetailsService;

	private static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable().authorizeRequests()
				.antMatchers("/users/getToken", "/h2-console/", "/v2/api-docs", "/configuration/ui",
						"/configuration/security", "/webjars/**","/swagger-ui/","/swagger-ui.html/","/**")
				.permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers().frameOptions().sameOrigin();
		//
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		logger.info("START");
		web.ignoring().antMatchers("users/", "/h2-console/**", "/v2/api-docs", "/configuration/ui",
				"/configuration/security", "/webjars/**","/**");
		logger.info("END");

	}

}
