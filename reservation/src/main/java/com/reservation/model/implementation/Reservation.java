package com.reservation.model.implementation;

import com.reservation.model.ICard;
import com.reservation.model.IReservation;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;

import java.util.Date;

public class Reservation implements IReservation {
    private Date fromDate;
    private Date toDate;
    private Long guestId;
    private Long hotelId;
    private Long reservationId;
    private ReservationStatus state;
    private String roomType;
    private IGuest guest;
    private IHotel hotel;
    private double amount;

    private ICard card;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ICard getCard() {
        return card;
    }

    public void setCard(ICard card) {
        this.card = card;
    }

    public IGuest getGuest() {
        return guest;
    }

    public void setGuest(IGuest guest) {
        this.guest = guest;
    }

    public IHotel getHotel() {
        return hotel;
    }

    public void setHotel(IHotel hotel) {
        this.hotel = hotel;
    }

    public Reservation(){}

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public ReservationStatus getState() {
        return state;
    }

    public void setState(ReservationStatus state) {
        this.state = state;
    }

    public Reservation(Date fromDate, Date toDate, Long guestId, Long hotelId, Long reservationId, ReservationStatus state, String roomType, ICard card) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.guestId = guestId;
        this.hotelId = hotelId;
        this.reservationId = reservationId;
        this.state = state;
        this.roomType = roomType;
        this.card = card;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", guestId=" + guestId +
                ", hotelId=" + hotelId +
                ", reservationId=" + reservationId +
                ", state='" + state + '\'' +
                ", roomType='" + roomType + '\'' +
                ", guest=" + guest +
                ", hotel=" + hotel +
                ", card=" + card +
                '}';
    }
}
