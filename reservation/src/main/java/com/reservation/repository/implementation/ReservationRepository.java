package com.reservation.repository.implementation;

import com.reservation.dto.ReservationDTO;
import com.reservation.repository.IReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {

    @Autowired
    private IReservationRepository reservationRepository;

    public ReservationDTO save(ReservationDTO input){
        return  reservationRepository.save(input);
    }
}
