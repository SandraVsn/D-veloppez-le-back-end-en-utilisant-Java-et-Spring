package com.openclassrooms.chatop.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateRentalDto extends UpdateRentalDto {

	private MultipartFile picture;

}
