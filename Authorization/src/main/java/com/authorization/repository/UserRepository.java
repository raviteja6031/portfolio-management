package com.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authorization.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUnameAndPasswd(String uname, String passwd);
	User findByUname(String uname);

}
