package com.openclassrooms.chatop.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RentalDto {
	
	private Long id;

    private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private String name;

	private float surface;
	
	private float price;
	
	private String description;
	
	private String[] picture;

	private Long owner_id;

}
