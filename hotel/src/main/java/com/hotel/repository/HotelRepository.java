package com.hotel.repository;

import com.hotel.dto.HotelDTO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<HotelDTO, Long> {
}
