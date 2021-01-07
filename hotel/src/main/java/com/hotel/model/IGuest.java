package com.hotel.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hotel.model.implementation.Guest;


@JsonDeserialize(as = Guest.class)
public interface IGuest {

    public void setGuestId(Long guestId);

    public Long getGuestId();

    public String getName();

    public void setName(String name);

    public String getContactNumber();

    public void setContactNumber(String contactNumber);

    public void setEmail(String email);

    public String getEmail() ;
}
