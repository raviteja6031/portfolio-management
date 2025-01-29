package com.customer.portal.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.customer.portal.client.UserAuthorizationClient;
import com.customer.portal.vo.JwtRequestVO;

@Controller
public class LoginController {

	@Autowired
	UserAuthorizationClient userAuthorizationClient;

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/loginForm")
	public String loginForm() {
		logger.info("Inside Login Form");
		return "loginForm1";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("JwtRequestVO") JwtRequestVO jwtRequestVO, HttpSession session, ModelMap map) {
		logger.info("Inside Login Function - LOGIC");
		String token = "";
		String msg = "";
		String url = "";
		try {
			token = userAuthorizationClient.createAuthenticationToken(jwtRequestVO).getBody();
			msg = "<p style='color:green'>Logged in successfully!!!</p>";
			logger.info(msg);
			url = "redirect:/welcome";
			logger.info(token);
			token = "Bearer " + token;
			session.setAttribute("token", token);
			session.setAttribute("uid", userAuthorizationClient.getById(token).getBody());
			session.setAttribute("username", userAuthorizationClient.getUname(token).getBody());
		} catch (Exception e) {
			msg = "Invalid Credentials";
			url = "loginForm2";
			logger.info(msg);
		}
		//logger.info(session.getAttribute("uid").toString());
		logger.info(url);
		map.addAttribute("msg", msg);
		logger.info("OUT OF LOGIN");
		return url;
	}

	@GetMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		logger.info("Inside Logout");
		session.invalidate();
		ModelAndView model = new ModelAndView("loginForm1");
		model.addObject("msg", "<b style='color:green'>Logged out successfully</b>");
		logger.info("LOGGED OUT");
		return model;
	}

}
