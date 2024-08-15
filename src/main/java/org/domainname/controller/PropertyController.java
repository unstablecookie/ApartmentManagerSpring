package org.domainname.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.domainname.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
		model.addAttribute("pageid", 1);
		return "redirect:/property/";
	}
	
	@RequestMapping(value="/property",method=GET)
	public String property(
			@RequestParam(value = "pageid", required = false) int pageid,
			Model model){
		int pageSize = 10;
		List<Property> list = propertyService.listAllPaged(new PageRequest(pageid-1,pageSize));
		Map<Long, String> map = new HashMap<>();
		for(Property p:list) {
			map.put(p.getId(), Base64.getEncoder().encodeToString(p.getPhoto()));
		}
		model.addAttribute("propertylist",list);
		model.addAttribute("images",map);
		model.addAttribute("pageid",pageid);
		logger.info("/property with GET method requested");
		return "property";
	}
	
	@RequestMapping(value="/propertysorttype",method=GET)
	public String propertySortedType(
			@RequestParam(value = "pageid", required = false) int pageid,
			Model model){
		int pageSize = 10;
		Sort sort = new Sort("type");
		List<Property> list = propertyService.listAllPaged(new PageRequest(pageid-1,pageSize,sort));
		Map<Long, String> map = new HashMap<>();
		for(Property p:list) {
			map.put(p.getId(), Base64.getEncoder().encodeToString(p.getPhoto()));
		}
		model.addAttribute("propertylist",list);
		model.addAttribute("images",map);
		model.addAttribute("pageid",pageid);
		logger.info("/propertysorttype with GET method requested");
		return "propertysorttype";
	}
	
	@RequestMapping(value="/propertysortarea",method=GET)
	public String propertySortedArea(
			@RequestParam(value = "pageid", required = false) int pageid,
			Model model){
		int pageSize = 10;
		Sort sort = new Sort("area");
		List<Property> list = propertyService.listAllPaged(new PageRequest(pageid-1,pageSize,sort));
		Map<Long, String> map = new HashMap<>();
		for(Property p:list) {
			map.put(p.getId(), Base64.getEncoder().encodeToString(p.getPhoto()));
		}
		model.addAttribute("propertylist",list);
		model.addAttribute("images",map);
		model.addAttribute("pageid",pageid);
		logger.info("/propertysortarea with GET method requested");
		return "propertysortarea";
	}
	
	@RequestMapping(value="/propertysortbuild",method=GET)
	public String propertySortedBuild(
			@RequestParam(value = "pageid", required = false) int pageid,
			Model model){
		int pageSize = 10;
		Sort sort = new Sort("build");
		List<Property> list = propertyService.listAllPaged(new PageRequest(pageid-1,pageSize,sort));
		Map<Long, String> map = new HashMap<>();
		for(Property p:list) {
			map.put(p.getId(), Base64.getEncoder().encodeToString(p.getPhoto()));
		}
		model.addAttribute("propertylist",list);
		model.addAttribute("images",map);
		model.addAttribute("pageid",pageid);
		logger.info("/propertysortbuild with GET method requested");
		return "propertysortbuild";
	}
	
	@RequestMapping(value="/delete_property",method=POST)
	public String deletePropertyById(
			@RequestParam("propertydel") Long propertyiddel,
			Model model)throws IOException {
		propertyService.deleteProperty(propertyiddel);
		model.addAttribute("pageid", 1);
		logger.info("DELETE -> propertyiddel : "+propertyiddel);
		return "redirect:/property";
	}
	
}
