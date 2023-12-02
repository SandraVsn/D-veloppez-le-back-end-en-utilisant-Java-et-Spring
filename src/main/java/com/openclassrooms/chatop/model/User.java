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
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name = "created_at")
	private LocalDateTime createdAt;
	
    @Column(name = "updated_at")
	private LocalDateTime updatedAt;

	private String name;

	@Column
	private String email;
	
	private String password;

}
