package com.reservation.repository.implementation;

import com.reservation.entity.ReservationEntity;
import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private IReservationRepository reservationRepository;

    public ReservationEntity save(ReservationEntity input){
        return  reservationRepository.save(input);
    }

    public ReservationEntity getReservationById(Long id) throws ReservationEntityNotFoundException {
        Optional<ReservationEntity> reservationDTO = reservationRepository.findById(id);
        return reservationDTO.isPresent() ? reservationDTO.get(): reservationDTO.orElseThrow(() ->new ReservationEntityNotFoundException("Reservation Entity not found for ID "+id));
    }
}
