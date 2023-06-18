package org.domainname.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.domainname.entity.User;


public interface UserService {
	
	List<User> fetchUsers();
	User getById(Long id);
	User saveUser(User user);
	List<User> listAll(String keyword);
	List<User> fetchUsersSortedUsername();
	

}
