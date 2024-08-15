package org.domainname.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.domainname.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.domainname.service.PropertyService;
import org.domainname.service.UserService;
import org.domainname.entity.User;
import org.domainname.entity.Property;
import java.io.IOException;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Controller
public class UsersController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired PropertyService propertyService;
	@Autowired UserService userService;
	
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
		model.addAttribute("pageid", 1);
		logger.info("/users/register with method POST requested");
		return "redirect:/users/";
	}
	
	@RequestMapping(value="/users",method=GET)
	public String users(
			@RequestParam(value = "pageid", required = false) int pageid,
			Model model) {
		int pageSize = 5;
		List<User> list = userService.listAllPaged(new PageRequest(pageid-1,pageSize));
		model.addAttribute("userslist",list);
		model.addAttribute("pageid",pageid);
		logger.info("/users with method GET requested");
		return "users";
	}
	
	@RequestMapping(value="/userssortfirstname",method=GET)
	public String usersSortedFirstname(
			@RequestParam(value = "pageid", required = false) int pageid,
			Model model) {
		int pageSize = 5;
		Sort sort = new Sort("firstName");
		List<User> list = userService.listAllPaged(new PageRequest(pageid-1,pageSize,sort));
		model.addAttribute("userslist",list);
		model.addAttribute("pageid",pageid);
		logger.info("/userssortfirstname with method GET requested");
		return "userssortfirstname";
	}
	
	@RequestMapping(value="/userssortlastname",method=GET)
	public String usersSortedLastname(
			@RequestParam(value = "pageid", required = false) int pageid,
			Model model) {
		int pageSize = 5;
		Sort sort = new Sort("lastName");
		List<User> list = userService.listAllPaged(new PageRequest(pageid-1,pageSize,sort));
		model.addAttribute("userslist",list);
		model.addAttribute("pageid",pageid);
		logger.info("/userssortlastname with method GET requested");
		return "userssortlastname";
	}
	
	@RequestMapping(value="/userssortusername",method=GET)
	public String usersSortedUsername(
			@RequestParam(value = "pageid", required = false) int pageid,
			Model model) {
		int pageSize = 5;
		Sort sort = new Sort("userName");
		List<User> list = userService.listAllPaged(new PageRequest(pageid-1,pageSize,sort));
		model.addAttribute("userslist",list);
		model.addAttribute("pageid",pageid);
		logger.info("/userssortusername with method GET requested");
		return "userssortusername";
	}
	
	@RequestMapping(value="/delete_user",method=POST)
	public String deletePropertyById(
			@RequestParam("userdel") Long useriddel,
			Model model)throws IOException {
		User user = userService.getById(useriddel);
		List<Property> list = propertyService.listByUser(user);
		if(list.size()>0) {
			for(Property p: list) {
				propertyService.deleteProperty(p);
			}
		}
		userService.deleteUser(useriddel);
		model.addAttribute("pageid", 1);
		logger.info("DELETE -> useriddel : "+useriddel);
		return "redirect:/users";
	}
	
}
