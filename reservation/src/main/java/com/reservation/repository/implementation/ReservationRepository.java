package com.reservation.repository.implementation;

import com.reservation.dto.ReservationDTO;
import com.reservation.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private IReservationRepository reservationRepository;

    public ReservationDTO save(ReservationDTO input){
        return  reservationRepository.save(input);
    }

    public ReservationDTO getReservationById(Long id){
        Optional<ReservationDTO> reservationDTO = reservationRepository.findById(id);
        return reservationDTO.isPresent() ? reservationDTO.get(): reservationDTO.orElseThrow(() ->new EntityNotFoundException("Reservation not found for id "+id));
    }
}
