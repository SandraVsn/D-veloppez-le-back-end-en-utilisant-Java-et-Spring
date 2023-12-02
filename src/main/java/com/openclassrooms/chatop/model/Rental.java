package com.openclassrooms.chatop.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private LocalDateTime created_at;
	
	private LocalDateTime updated_at;

	private String name;

	private float surface;
	
	private float price;
	
	private String description;
	
	private String picture;
	
	private Long owner_id;

}
