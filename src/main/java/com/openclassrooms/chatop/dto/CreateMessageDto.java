package com.openclassrooms.chatop.dto;

import lombok.Data;

@Data
public class CreateMessageDto {
	
	private String message;
	
	private long rentalId;
	
	private long userId;

}
