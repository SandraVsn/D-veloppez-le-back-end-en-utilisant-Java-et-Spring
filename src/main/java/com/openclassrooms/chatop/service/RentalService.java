package com.openclassrooms.chatop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {
	
private RentalRepository rentalRepository;
	
	@Autowired
	public RentalService(RentalRepository rentalRepository) {
		this.rentalRepository = rentalRepository;
	}
	
	public Optional<Rental> getRental(final Long id){
		return rentalRepository.findById(id);
	}
	
	public Iterable<Rental> getRentals(){
		return rentalRepository.findAll();
	}
	
	public Rental saveRental(Rental rental) {
		return rentalRepository.save(rental);

	}

}
