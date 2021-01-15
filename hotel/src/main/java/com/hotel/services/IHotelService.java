package com.hotel.services;

import com.hotel.model.IHotel;
import com.hotel.model.IReservation;

import java.util.Date;
import java.util.List;

public interface IHotelService {
    IHotel addNewHotel(IHotel hotel);
    IReservation cancelReservation(Long hotelId, Long reservationId);
    IReservation reservationRequest(Long hotelId, IReservation reservation);
    List<IReservation> getAllReservationsByHotelId(Long hotelId);
    List<IReservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId );
    public IReservation confirmReservation(Long reservationId);
    //public List<IHotel> getHotelsByCityAndDateRange(String cityName, Date fromDate, Date toDate);

    public List<IHotel> getHotels(List<Long> hotelIds);
    public IHotel getHotelById(Long hotelId);

    public List<IHotel> searchHotels(String city, Date fromDate, Date toDate, String roomType );
}
