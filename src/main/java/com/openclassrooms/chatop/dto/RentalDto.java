package com.openclassrooms.chatop.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RentalDto {
	
	private Long id;

    private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String name;

	private float surface;
	
	private float price;
	
	private String description;
	
	private String[] picture;

	private int ownerId;

}
