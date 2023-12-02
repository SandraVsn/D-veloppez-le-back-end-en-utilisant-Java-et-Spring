package com.openclassrooms.chatop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.chatop.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByEmail(String email);
}