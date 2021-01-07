package com.hotel.services;

import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.Reservation;

import java.util.Date;
import java.util.List;

public interface IHotelService {
    IHotel addNewHotel(IHotel hotel);
    String cancelReservation(Long hotelId, Long reservationId);
    String reservationRequest(Long hotelId, IReservation reservation);
    List<IReservation> getAllReservationsByHotelId(Long hotelId);
    List<IReservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId );
    public String confirmReservation(Long reservationId);
    //public List<IHotel> getHotelsByCityAndDateRange(String cityName, Date fromDate, Date toDate);

    public List<IHotel> getHotels(List<Long> hotelIds);
    public IHotel getHotelById(Long hotelId);
}
