package com.guest.model.implementation;

import com.guest.model.IAddress;
import com.guest.model.IHotel;

public class Hotel implements IHotel {
    private String name;
    private String contactNumber;
    private IAddress address;
    private Long hotelId;

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getHotelId() {
        return hotelId;
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

    public IAddress getAddress() {
        return address;
    }

    public void setAddress(IAddress address) {
        this.address = address;
    }
}
