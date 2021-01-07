package com.reservation.repository;

import com.reservation.dto.ReservationDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends CrudRepository<ReservationDTO, Long> {
}
