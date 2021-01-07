package com.hotel.repository;

import com.hotel.dto.ReservationDTO;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationDTO, Long> {
}
