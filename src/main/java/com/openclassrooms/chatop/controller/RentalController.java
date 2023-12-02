package com.openclassrooms.chatop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
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
import com.openclassrooms.chatop.dto.RentalsDto;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.AuthService;
import com.openclassrooms.chatop.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "${apiPrefix}/rentals")
@Tag(name = "Rentals", description = "API for Rentals CRUD operations")
public class RentalController {
	
	private RentalService rentalService;
	private ModelMapper modelMapper;
	private AuthService authService; 
	
	@Autowired
	public RentalController(RentalService rentalService, ModelMapper modelMapper, AuthService authService) {
		this.rentalService = rentalService;
		this.modelMapper = modelMapper;
		this.authService = authService;
	}
	

	/* Endpoint to create a new Rental
	 * @param principal : The currently authenticated user.
	 * @param createRentalDto : The DTO containing the rental to create.
	 * @return : An ApiDefaultResponse indicating the success or failure of the rental creation.
	 * @throws IOException : Thrown in case of file I/O errors during image processing.
	 */
	@Operation(summary = "Creates a new Rental")
	@PostMapping()
	public ApiDefaultResponse createRental(Principal principal, @ModelAttribute CreateRentalDto createRentalDto) throws IOException {
		User user = authService.getMe(principal.getName());
		String imageUrl = "images/" + createRentalDto.getPicture().getOriginalFilename();
		String pathToWrite = "src/main/resources/static/" + imageUrl;
		Files.write(Paths.get(pathToWrite), createRentalDto.getPicture().getBytes());
		
		Rental rental = modelMapper.map(createRentalDto, Rental.class);
		rental.setPicture("http://localhost:9000/" + imageUrl);
		rental.setOwner_id(user.getId());
		rental.setCreated_at(LocalDateTime.now());
		
		Rental savedRental = rentalService.saveRental(rental);
		if(savedRental != null) {
			return new ApiDefaultResponse("Rental created !"); 
		} else {
			return null;
		}
	}

	/* Endpoint to get a Rental by its id
	 * @param id : The unique identifier of the rental.
	 * @return : A RentalDto representing the rental details, or null if not found.
	 */
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

	/* Endpoint to get all Rentals
	 * @return : A RentalsDto containing a list of RentalDto representing all rentals.
	 */
	@Operation(summary = "Get all Rentals")
	@GetMapping()
	public RentalsDto getRentals() {
	    Iterable<Rental> rentals = rentalService.getRentals();
	    List<RentalDto> rentalDtos = StreamSupport.stream(rentals.spliterator(), false)
	            .map(rental -> {
	                RentalDto rentalDto = modelMapper.map(rental, RentalDto.class);
	                rentalDto.setPicture(new String[]{rental.getPicture()});
	                return rentalDto;
	            })
	            .toList();

	    return new RentalsDto(rentalDtos);
	}
	

	/* Endpoint to update a Rental by its id
	 * @param id : The unique identifier of the rental to be updated.
	 * @param rentalDto : The DTO containing updated rental information.
	 * @return : An ApiDefaultResponse indicating the success or failure of the update.
	 */
	@Operation(summary = "Update a Rental by its id")
	@PutMapping("/{id}")
	public ApiDefaultResponse updateRental(@PathVariable("id") final Long id, @ModelAttribute CreateRentalDto rentalDto) {
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
			currentRental.setUpdated_at(LocalDateTime.now());
			rentalService.saveRental(currentRental);
			return new ApiDefaultResponse("Rental updated !");
		} else {
			return null;
		}
	}
	
}
