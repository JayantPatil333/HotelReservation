package com.reservation.mapper.implementation;

import com.reservation.dto.ReservationDTO;
import com.reservation.mapper.IMapper;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.Reservation;

public class Mapper implements IMapper {
    public ReservationDTO mapIReservationToReservationDTO(IReservation reservation){
        ReservationDTO reservationDTO =  new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setFromDate(reservation.getFromDate());
        reservationDTO.setGuestId(reservation.getGuestId());
        reservationDTO.setState(reservation.getState());
        reservationDTO.setHotelId(reservation.getHotelId());
        reservationDTO.setToDate(reservation.getToDate());
        return reservationDTO;
    }

    public IReservation mapReservationDTOToIReservation(ReservationDTO reservationDTO){
        IReservation reservation =  new Reservation();
        reservation.setReservationId(reservationDTO.getReservationId());
        reservation.setFromDate(reservationDTO.getFromDate());
        reservation.setGuestId(reservationDTO.getGuestId());
        reservation.setHotelId(reservationDTO.getHotelId());
        reservation.setState(reservationDTO.getState());
        reservation.setToDate(reservationDTO.getToDate());
        return reservation;
    }
}
