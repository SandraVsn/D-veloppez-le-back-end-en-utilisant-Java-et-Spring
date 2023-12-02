package com.openclassrooms.chatop.dto;

import lombok.Data;

@Data
public class CreateUserDto {
	
	private String name;

	private String email;
	
	private String password;

}
