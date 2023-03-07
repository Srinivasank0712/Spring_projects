package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public User findByUserEmail(String userEmail);
	public User findByUserPhone(String userPhone);
}
