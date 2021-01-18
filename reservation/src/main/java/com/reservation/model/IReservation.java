package com.reservation.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reservation.model.implementation.Reservation;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;

import java.util.Date;

@JsonDeserialize(as = Reservation.class)
public interface IReservation {

    public ICard getCard();

    public void setCard(ICard card);
    public Date getFromDate();

    public void setFromDate(Date fromDate);

    public Date getToDate();

    public void setToDate(Date toDate);

    public Long getGuestId();

    public void setGuestId(Long guestId);

    public Long getHotelId();

    public void setHotelId(Long hotelId);

    public Long getReservationId();

    public void setReservationId(Long reservationId);

    public String getState();

    public void setState(String state);

    public String getRoomType();

    public void setRoomType(String roomType);

    public IGuest getGuest();

    public void setGuest(IGuest guest);

    public IHotel getHotel();

    public void setHotel(IHotel hotel);
}
