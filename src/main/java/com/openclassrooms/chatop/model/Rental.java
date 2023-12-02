package com.openclassrooms.chatop.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
	
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
	
    @Column(name = "updated_at")
	private LocalDateTime updatedAt;

	private String name;

	private float surface;
	
	private float price;
	
	private String description;
	
	private String picture;
	
    @Column(name = "owner_id")
	private int ownerId;

}
