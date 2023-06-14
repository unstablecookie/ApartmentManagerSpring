package org.domainname.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.domainname.entity.User;
import org.domainname.entity.Property;
import org.domainname.service.PropertyService;
import org.domainname.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.core.context.SecurityContextHolder;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired public UserService userService ;
	@Autowired PropertyService propertyService;
	
	@RequestMapping(value="/", method=GET)
	public String home() {
		logger.info("/ with method GET requested");
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String viewLoginPage() {
		logger.info("user is trying to login");
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    logger.info("user logged out");
	    return "redirect:/login";
	}
	
	@RequestMapping(value="/search",method=GET)
	public String searchUser(Model model,@Param("keyword") String keyword) {
		List<User> uList = userService.listAll(keyword);
		List<Property> pList = propertyService.listAll(keyword);
		Map<Long, String> map = new HashMap<>();
		for(Property p:pList) {
			map.put(p.getId(), Base64.getEncoder().encodeToString(p.getPhoto()));
		}
		model.addAttribute("listusers", uList);
		model.addAttribute("listproperty", pList);
		model.addAttribute("images",map);
		model.addAttribute("keyword", keyword);
		return "search";
	}
}
