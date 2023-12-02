package com.openclassrooms.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.chatop.model.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

}
