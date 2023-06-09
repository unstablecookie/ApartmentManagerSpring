package org.domainname.service;

import org.domainname.repository.UserRepository;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.domainname.entity.User;
import org.domainname.service.UserService;
import org.domainname.service.UserServiceImp;

@Service
public class UserServiceImp implements UserService{
	

	private UserRepository userRepository;
	
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
}
