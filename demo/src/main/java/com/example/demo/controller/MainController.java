package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.User;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {
	
	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	  @Autowired
	  UserService userService;

	  @Autowired
	  private SecurityService securityService;
	
	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("/");
		logger.info(request.getRemoteAddr());
		return "index";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		logger.debug("/login");
		return "login/login";
	}

	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String registration(Model model){
		logger.debug("/registration");
		return "login/registration";
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String registration(@ModelAttribute("myForm") User userForm, BindingResult bindingResult, Model model ,String[] roles) throws Exception {
		String password = userForm.getPassword();
		userService.saveUser(userForm, roles);
		securityService.autologin(userForm.getUsername(), password);
		return "redirect:/";
	}

	/*
	@RequestMapping("loginProcess")
	public String loginProcess(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model ,String[] roles) throws Exception {
		logger.debug("/loginProcess");
		
		String password = userForm.getPassword();
		userService.saveUser(userForm, roles);
		securityService.autologin(userForm.getUsername(), password);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "template", method = RequestMethod.GET)
	public ModelAndView template() {
		logger.debug("/template");
		return new ModelAndView("template");
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.debug("/login");
		return new ModelAndView("login");
	}

	@RequestMapping(value = "admin/", method = RequestMethod.GET)
	public ModelAndView admin() {
		logger.debug("/admin");
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "admin/newpage", method = RequestMethod.GET)
	public ModelAndView newpage() {
		logger.debug("/admin/newpage");
		return new ModelAndView("newpage");
	}
	*/
}