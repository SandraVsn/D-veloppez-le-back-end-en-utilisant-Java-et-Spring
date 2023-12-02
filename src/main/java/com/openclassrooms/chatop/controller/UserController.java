package com.openclassrooms.chatop.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "${apiPrefix}/user")
@Tag(name = "User", description = "API for User CRUD operations")
public class UserController {
	
	private UserService userService;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
		

	/* Endpoint to get a User by its id
	 * @param id : The unique identifier of the user.
	 * @return : A UserDto representing the user details, or null if not found.
	 */
	@Operation(summary = "Get a User by its id")
	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable("id") final Long id) {
		Optional<User> user = userService.getUser(id);
		if(user.isPresent()) {
			return modelMapper.map(user.get(), UserDto.class); 
		} else {
			return null;
		}
	}
	
}
