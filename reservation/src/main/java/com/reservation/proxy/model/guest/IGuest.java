package com.reservation.proxy.model.guest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reservation.proxy.model.guest.implementation.Guest;

@JsonDeserialize(as = Guest.class)
public interface IGuest {

    public Long getGuestId();

    public void setGuestId(Long guestId);

    public String getName();

    public void setName(String name) ;

    public String getEmail() ;

    public void setEmail(String email);

    public String getContactNumber();

    public void setContactNumber(String contactNumber);

    public int getRatting();

    public void setRatting(int ratting);
}
