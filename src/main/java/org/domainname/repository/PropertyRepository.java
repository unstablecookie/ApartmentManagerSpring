package org.domainname.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.domainname.entity.Property;
import org.domainname.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {
	@Query("SELECT p FROM Property p WHERE CONCAT(p.type, ' ', p.area, ' ', p.build)  LIKE %?1% ")
	public List<Property> search(String keyword);

}
