package com.openclassrooms.chatop.controller;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.CreateUserDto;
import com.openclassrooms.chatop.dto.LoginDto;
import com.openclassrooms.chatop.dto.TokenDto;
import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.AuthService;
import com.openclassrooms.chatop.service.JWTService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "${apiPrefix}/auth")
@Tag(name = "Authentication", description = "API for Authentication operations")
public class AuthController {
	
	private AuthService authService;
	private ModelMapper modelMapper;
	private JWTService jwtService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthController(AuthService authService, ModelMapper modelMapper, JWTService jwtService, PasswordEncoder passwordEncoder) {
		this.authService = authService;
		this.modelMapper = modelMapper;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
	}
		

	@Operation(summary = "Creates and log in new User, returns the jwt token")
	@PostMapping("register")
	public TokenDto register(@RequestBody CreateUserDto createUserDto) {
		User user = modelMapper.map(createUserDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User createdUser = authService.register(user);
		if(createdUser != null) {
			return new TokenDto(jwtService.generateToken(createdUser));
		} else {
			return null;
		}
	}
	
	@Operation(summary = "Log in User and returns the jwt token")
	@PostMapping("login")

	public TokenDto login(Authentication authentication, @RequestBody LoginDto loginDto) {
	    User user = modelMapper.map(loginDto, User.class);
		return new TokenDto(jwtService.generateToken(user));
	}	

	@Operation(summary = "Get current logged in User")
	@GetMapping("me")
	public Object getMe(Principal principal){
		User user = authService.getMe(principal.getName());
		return modelMapper.map(user, UserDto.class);
	}
	
}