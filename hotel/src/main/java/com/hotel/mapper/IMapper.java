package com.hotel.mapper;

import com.hotel.dto.HotelDTO;
import com.hotel.dto.ReservationDTO;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;

public interface IMapper {
    HotelDTO mapIHotelToHotelDTO(IHotel hotel);

    IHotel mapHotelDTOToIHotel(HotelDTO hotelDTO);

    IReservation mapReservationDTOToIReservation(ReservationDTO reservationDTO);

    ReservationDTO mapIReservationToReservationDTO(IReservation iReservation);

    //IGuest mapGuestToIGuest(com.hotel.proxy.model.IGuest guest);
}
