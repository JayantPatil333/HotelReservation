package com.reservation.service;

import com.reservation.model.IReservation;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;

public interface IReservationService {
    public IGuest getGuestById(Long guestId);

    public IHotel getHotelById(Long hotelId);

    public String requestForReservation(IReservation reservation);

    public String confirmReservation(IReservation reservation);

}
