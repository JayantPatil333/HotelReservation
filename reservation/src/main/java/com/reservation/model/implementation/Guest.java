package com.reservation.model.implementation;

import com.reservation.model.IGuest;
import com.reservation.model.IReservation;

import java.util.ArrayList;
import java.util.List;

public class Guest implements IGuest {

    private Long guestId;
    private String name;
    private String email;
    private String contactNumber;
    private int ratting;
    private List<IReservation> reservations =  new ArrayList<>();

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }

    public List<IReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<IReservation> reservations) {
        this.reservations = reservations;
    }

    public Guest() {
    }

    public Guest(Long guestId, String name, String email, String contactNumber, int ratting, List<IReservation> reservations) {
        this.guestId = guestId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.ratting = ratting;
        this.reservations = reservations;
    }
}
