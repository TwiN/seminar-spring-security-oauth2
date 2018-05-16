package org.twinnation.seminar.springsecurity.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.twinnation.seminar.springsecurity.bean.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
}
