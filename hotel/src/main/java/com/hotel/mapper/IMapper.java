package com.hotel.mapper;

import com.hotel.dto.HotelDTO;
import com.hotel.dto.ReservationDTO;
import com.hotel.model.IGuest;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.proxy.model.Guest;

public interface IMapper {
    HotelDTO mapIHotelToHotelDTO(IHotel hotel);

    IHotel mapHotelDTOToIHotel(HotelDTO hotelDTO);

    IReservation mapReservationDTOToIReservation(ReservationDTO reservationDTO);

    ReservationDTO mapIReservationToReservationDTO(IReservation iReservation);

    IGuest mapGuestToIGuest(Guest guest);
}
