package com.guest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.guest.model.implementation.Hotel;

@JsonDeserialize(as = Hotel.class)
public interface IHotel {

    public void setHotelId(Long hotelId);

    public Long getHotelId();

    public String getName();

    public void setName(String name);

    public String getContactNumber();

    public void setContactNumber(String contactNumber);

    public IAddress getAddress();

    public void setAddress(IAddress address);
}
