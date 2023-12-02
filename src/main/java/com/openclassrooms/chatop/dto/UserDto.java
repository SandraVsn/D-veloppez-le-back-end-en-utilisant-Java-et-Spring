package com.openclassrooms.chatop.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	
	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private String name;

	private String email;

}
