package com.guest.proxy.model.implementation;

import com.guest.proxy.model.IAddress;
import com.guest.proxy.model.IHotel;

import java.util.Objects;

public class Hotel implements IHotel {
    private Long hotelId;
    private String name;
    private String phoneNumber;
    private int starRatting;
    private IAddress address;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStarRatting() {
        return starRatting;
    }

    public void setStarRatting(int starRatting) {
        this.starRatting = starRatting;
    }

    public IAddress getAddress() {
        return address;
    }

    public void setAddress(IAddress address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(hotelId, hotel.hotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId);
    }
}
