package com.reservation.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reservation")
public class ReservationEntity {
    private Date fromDate;
    private Date toDate;
    private Long guestId;
    private Long hotelId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    private String state;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cardId")
    private CardEntity card;

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ReservationEntity() {
    }

    public ReservationEntity(Date fromDate, Date toDate, Long guestId, Long hotelId, Long reservationId, String state, CardEntity card) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.guestId = guestId;
        this.hotelId = hotelId;
        this.reservationId = reservationId;
        this.state = state;
        this.card = card;
    }
}
