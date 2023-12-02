package com.openclassrooms.chatop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.ApiDefaultResponse;
import com.openclassrooms.chatop.dto.CreateRentalDto;
import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "${apiPrefix}/rentals")
@Tag(name = "Rentals", description = "API for Rentals CRUD operations")
public class RentalController {
	
	private RentalService rentalService;
	private ModelMapper modelMapper;
	
	@Autowired
	public RentalController(RentalService rentalService, ModelMapper modelMapper) {
		this.rentalService = rentalService;
		this.modelMapper = modelMapper;
	}
	

	@Operation(summary = "Creates a new Rental")
	@PostMapping()
//	https://www.baeldung.com/sprint-boot-multipart-requests
	public ApiDefaultResponse createRental(@ModelAttribute CreateRentalDto createRentalDto) throws IOException {
		// TODO : get current user by token 
		
		int userId = 1;
		
		String imageUrl = "src/main/resources/static/images/" + createRentalDto.getPicture().getOriginalFilename();
		Files.write(Paths.get(imageUrl), createRentalDto.getPicture().getBytes());
		
		Rental rental = modelMapper.map(createRentalDto, Rental.class);
		rental.setPicture(imageUrl);
		rental.setOwnerId(userId);
		rental.setCreatedAt(LocalDateTime.now());
		
		Rental savedRental = rentalService.saveRental(rental);
		if(savedRental != null) {
			return new ApiDefaultResponse("Rental created !"); 
		} else {
			return null;
		}
	}

	@Operation(summary = "Get a Rental by its id")
	@GetMapping("/{id}")
	public RentalDto getRental(@PathVariable("id") final Long id) {
		Optional<Rental> rental = rentalService.getRental(id);
		if(rental.isPresent()) {
			RentalDto rentalDto = modelMapper.map(rental.get(), RentalDto.class);
			rentalDto.setPicture(new String[]{rental.get().getPicture()});
			return rentalDto;
		} else {
			return null;
		}
	}

	@Operation(summary = "Get all Rentals")
	@GetMapping()
	public List<RentalDto> getRentals() {
	    Iterable<Rental> rentals = rentalService.getRentals();

	    return StreamSupport.stream(rentals.spliterator(), false)
	            .map(rental -> {
	                RentalDto rentalDto = modelMapper.map(rental, RentalDto.class);
	                rentalDto.setPicture(new String[]{rental.getPicture()});
	                return rentalDto;
	            })
	            .toList();
	}
	

	@Operation(summary = "Update a Rental by its id")
	@PutMapping("/{id}")
	public ApiDefaultResponse updateRental(@PathVariable("id") final Long id, @ModelAttribute CreateRentalDto rentalDto) {
		//TODO: Check if user who wants to update the rental is the owner !
		Optional<Rental> r = rentalService.getRental(id);
		if(r.isPresent()) {
			Rental currentRental = r.get();
			
			String name = rentalDto.getName();
			if(name != null) {
				currentRental.setName(name);
			}
			Float surface = rentalDto.getSurface();
			if(surface != null) {
				currentRental.setSurface(surface);
			}
			Float price = rentalDto.getPrice();
			if(price != null) {
				currentRental.setPrice(price);
			}
			String description = rentalDto.getDescription();
			if(description != null) {
				currentRental.setDescription(description);
			}
			currentRental.setUpdatedAt(LocalDateTime.now());
			rentalService.saveRental(currentRental);
			return new ApiDefaultResponse("Rental updated !");
		} else {
			return null;
		}
	}
	
}
