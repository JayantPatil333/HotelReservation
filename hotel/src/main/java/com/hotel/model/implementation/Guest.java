package com.hotel.model.implementation;


import com.hotel.model.IGuest;

public class Guest implements IGuest {
    private java.lang.Long guestId;
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
    public void setGuestId(java.lang.Long guestId) {
        this.guestId = guestId;
    }

    public java.lang.Long getGuestId() {
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
