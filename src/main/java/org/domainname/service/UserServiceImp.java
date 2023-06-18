package org.domainname.service;

import org.domainname.repository.UserRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import org.domainname.entity.User;
import org.domainname.service.UserService;
import org.domainname.service.UserServiceImp;
import org.springframework.data.domain.Sort;

@Service
public class UserServiceImp implements UserService{
	

	private UserRepository userRepository;
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
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
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
}
