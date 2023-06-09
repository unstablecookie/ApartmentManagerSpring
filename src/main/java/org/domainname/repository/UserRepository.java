package org.domainname.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.domainname.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
}
