package org.domainname.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.domainname.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserPagingRepository extends PagingAndSortingRepository<User,Integer>{
		
}
