package com.hotel.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hotel.model.implementation.Reservation;

import java.util.Date;

@JsonDeserialize(as = Reservation.class)
public interface IReservation {
    Long getReservationId();
    void setState(String state);
    String getState();
    public IRoom getRoom() ;
    public void setRoom(IRoom room);
    public IGuest getGuest();
    public void setGuest(IGuest guest);
    public Date getFromDate();
    public void setFromDate(Date fromDate);
    public Date getToDate();
    public void setToDate(Date toDate);
    public void setReservationId(Long reservationId);

}
