package org.domainname.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.domainname.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.domainname.service.PropertyServiceImp;
import org.domainname.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.domainname.service.PropertyService;
import org.domainname.entity.Property;
import java.util.List;
import org.springframework.ui.Model;
import java.util.Base64;
import org.apache.commons.lang3.ArrayUtils;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class PropertyController {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);
	
	@Autowired PropertyService propertyService;
	@Autowired UserService userService;
	
	@RequestMapping(value="/property",method=GET)
	public String home(Model model){
		List<Property> list = propertyService.fetchProperty();
		Map<Long, String> map = new HashMap<>();
		for(Property p:list) {
			map.put(p.getId(), Base64.getEncoder().encodeToString(p.getPhoto()));
		}
		model.addAttribute("propertylist",list);
		model.addAttribute("images",map);
		logger.info("/property with GET method requested");
		return "property";
	}
	
	@RequestMapping(value="/property/{propertyid}",method=GET)
	public String propertyId(@PathVariable String propertyid,Model model) {
		Property property = propertyService.getById(Long.valueOf(propertyid));
		User user = property.getUser();
		String imageString = Base64.getEncoder().encodeToString(property.getPhoto());
		model.addAttribute("propertyid", property);
		model.addAttribute("owner", user);
		model.addAttribute("photo", imageString);
		return "propertyid";
	}
	
	@RequestMapping(value="/property/register",method=GET)
	public String newProperty(Model model) {
		model.addAttribute("property", new Property());
		List<User> list = userService.fetchUsers();
		model.addAttribute("userslist",list);
		return "registerproperty";
	}
	
	@RequestMapping(value="/property/register",method=POST) 
	public String registerProperty(Model model,@ModelAttribute Property property,
			@RequestParam("image1") MultipartFile multipartfile,
			@RequestParam("useridstring") String useridstring)throws IOException {
		property.setPhoto(multipartfile.getBytes());
		User user = userService.getById(Long.valueOf(useridstring));
		property.setUser(user);
		propertyService.saveProperty(property);
		return "redirect:/property/";
	}
	
}
