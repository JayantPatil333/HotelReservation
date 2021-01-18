package com.reservation.service;

import com.reservation.model.ICard;
import com.reservation.model.IReservation;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;


public interface IReservationService {
    public IGuest getGuestById(Long guestId);

    public IHotel getHotelById(Long hotelId);

    public String requestForReservation(IReservation reservation);

    public IReservation confirmReservation(IReservation reservation);

    public String doPayment(ICard card, double amount, Long reservationId);

    public IReservation getReservation(Long id, boolean isDetailsRequired);

    public IReservation cancelReservation(Long id, double amount) throws Exception;
}
