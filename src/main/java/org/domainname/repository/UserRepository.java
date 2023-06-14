package org.domainname.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.domainname.entity.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	@Query("SELECT p FROM User p WHERE p.userName LIKE %?1%")
	public List<User> search(String keyword);
}
