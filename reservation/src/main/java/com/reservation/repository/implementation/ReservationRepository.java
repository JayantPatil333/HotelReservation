package com.reservation.repository.implementation;

import com.reservation.entity.ReservationEntity;
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

    public ReservationEntity getReservationById(Long id){
        Optional<ReservationEntity> reservationDTO = reservationRepository.findById(id);
        return reservationDTO.isPresent() ? reservationDTO.get(): reservationDTO.orElseThrow(() ->new EntityNotFoundException("Reservation Entity not found for ID "+id));
    }
}
