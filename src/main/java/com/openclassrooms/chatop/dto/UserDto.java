package com.openclassrooms.chatop.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String name;

	private String email;

}
