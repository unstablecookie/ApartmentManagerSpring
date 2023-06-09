package org.domainname.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.domainname.entity.Property;

import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {

}
