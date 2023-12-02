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
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private LocalDateTime created_at;

	private LocalDateTime updated_at;
	
	private String message;
	
	@Column(name = "rental_id")
	private long rentalId;
	
	@Column(name = "user_id")
	private long userId;


}
