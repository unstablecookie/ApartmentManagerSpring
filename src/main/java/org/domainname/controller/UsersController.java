package org.domainname.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.domainname.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.domainname.service.UserService;
import org.domainname.entity.User;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.repository.query.Param;

@Controller
public class UsersController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired UserService userService;
	
	@RequestMapping(value="/users",method=GET)
	public String users(Model model) {
		List<User> list = userService.fetchUsers();
		model.addAttribute("userslist",list);
		logger.info("/users with method GET requested");
		return "users";
	}
	
	@RequestMapping(value="/userssortusername",method=GET)
	public String usersSortedUsername(Model model) {
		List<User> list = userService.fetchUsersSortedUsername();
		model.addAttribute("userslist",list);
		logger.info("/userssortusername with method GET requested");
		return "users";
	}
	
	@RequestMapping(value="/userssortfirstname",method=GET)
	public String usersSortedFirstname(Model model) {
		List<User> list = userService.fetchUsersSortedFirstname();
		model.addAttribute("userslist",list);
		logger.info("/userssortusername with method GET requested");
		return "users";
	}
	
	@RequestMapping(value="/userssortlastname",method=GET)
	public String usersSortedLastname(Model model) {
		List<User> list = userService.fetchUsersSortedLastname();
		model.addAttribute("userslist",list);
		logger.info("/userssortusername with method GET requested");
		return "users";
	}
	
	@RequestMapping(value="/users/{userid}",method=GET)
	public String usersId(@PathVariable String userid, Model model) {
		User user = userService.getById(Long.valueOf(userid));
		model.addAttribute("userid",user);
		logger.info("/users/{userid} with method GET requested");
		return "usersid";
	}
	
	@RequestMapping(value="/users/register",method=GET)
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		logger.info("/users/register with method GET requested");
		return "registeruser";
	}
	
	@RequestMapping(value="/users/register",method=POST)
	public String registerUser(Model model,@ModelAttribute User user) {
		User newUser = userService.saveUser(user);
		logger.info("/users/register with method POST requested");
		return "redirect:/users/";
		
	}
	
}
