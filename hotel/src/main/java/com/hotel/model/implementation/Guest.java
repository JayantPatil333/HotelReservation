package com.hotel.model.implementation;

import com.hotel.model.IGuest;

import javax.persistence.*;


public class Guest implements IGuest {
    private Long guestId;
    private String name;
    private String contactNumber;
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public Guest(){}
    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
