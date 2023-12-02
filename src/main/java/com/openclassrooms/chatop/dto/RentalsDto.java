package com.openclassrooms.chatop.dto;

import java.util.List;

import lombok.Data;

@Data
public class RentalsDto {
	
	 private List<RentalDto> rentals;

	    public RentalsDto(List<RentalDto> rentals) {
	        this.rentals = rentals;
	    }

}
