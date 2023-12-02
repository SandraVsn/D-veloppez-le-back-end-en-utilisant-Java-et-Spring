package com.openclassrooms.chatop.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class AuthService {

	private UserRepository userRepository;
	
	@Autowired
	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
		
	public User getMe(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User register(User user) {
		user.setCreatedAt(LocalDateTime.now());
		User userInDB = userRepository.findByEmail(user.getEmail());
		if(userInDB != null) {
			throw new IllegalArgumentException("User already exists");
		}
		return userRepository.save(user);
	}
	
}
