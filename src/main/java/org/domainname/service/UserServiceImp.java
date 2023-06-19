package org.domainname.service;

import org.domainname.repository.UserRepository;
import org.domainname.repository.UserPagingRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.domainname.controller.UsersController;
import org.domainname.entity.User;
import org.domainname.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@Service
public class UserServiceImp implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

	private UserRepository userRepository;
	private UserPagingRepository userPagingRepository;
	private Sort sortByName(){
		return new Sort(Sort.Direction.ASC, "userName");
	}
	private Sort sortByFirstName(){
		return new Sort(Sort.Direction.ASC, "firstName");
	}
	private Sort sortByLastName(){
		return new Sort(Sort.Direction.ASC, "lastName");
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository,UserPagingRepository userPagingRepository) {
		this.userRepository = userRepository;
		this.userPagingRepository = userPagingRepository;
	}

	@Transactional
	public List<User> fetchUsers(){
		return (List<User>)userRepository.findAll();
	}
	
	@Transactional
	public User getById(Long id) {
		return (User)userRepository.findOne(id);
	}
	
	@Transactional
	public User saveUser(User user) {
		return (User)userRepository.save(user);
	}
	
	@Transactional
	public List<User> listAll(String keyword){
		if(keyword != null) {
			return (List<User>)userRepository.search(keyword);
		}
		return  (List<User>)userRepository.findAll();
	}
	
	@Transactional
	public List<User> fetchUsersSortedUsername(){
		return (List<User>)userRepository.findAll(sortByName());
	}
	
	@Transactional
	public List<User> fetchUsersSortedFirstname(){
		return (List<User>)userRepository.findAll(sortByFirstName());
	}
	
	@Transactional
	public List<User> fetchUsersSortedLastname(){
		return (List<User>)userRepository.findAll(sortByLastName());
	}
	
	@Transactional 
	public List<User> listAllPaged(Pageable pageable){
		Page<User> page = (Page<User>)userPagingRepository.findAll(pageable);
		return page.getContent();
	}
}
