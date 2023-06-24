package org.domainname.service;

import java.util.List;
import org.domainname.entity.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
	
	List<User> fetchUsers();
	User getById(Long id);
	User saveUser(User user);
	List<User> listAll(String keyword);
	List<User> fetchUsersSortedUsername();
	List<User> fetchUsersSortedFirstname();
	List<User> fetchUsersSortedLastname();
	List<User> listAllPaged(Pageable pageable);
	void deleteSelected(List<User> list);
}
