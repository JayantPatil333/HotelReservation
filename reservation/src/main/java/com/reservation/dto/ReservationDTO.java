package com.reservation.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reservation")
public class ReservationDTO {
    private Date fromDate;
    private Date toDate;
    private Long guestId;
    private Long hotelId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
