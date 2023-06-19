package org.domainname.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.domainname.entity.User;

@Repository
public interface UserPagingRepository extends PagingAndSortingRepository<User,Integer>{
	
}
