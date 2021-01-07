package com.reservation.mapper;

import com.reservation.dto.ReservationDTO;
import com.reservation.model.IReservation;

public interface IMapper {

    public ReservationDTO mapIReservationToReservationDTO(IReservation reservation);

    public IReservation mapReservationDTOToIReservation(ReservationDTO reservationDTO);
}
