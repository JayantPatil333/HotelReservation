package com.guest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.guest.model.implementation.Address;

@JsonDeserialize(as = Address.class)
public interface IAddress {
    public void setAddressId(Long addressId);

    public Long getAddressId();

    public String getStreet();

    public void setStreet(String street);

    public String getLocation();

    public void setLocation(String location);

    public String getCity();

    public void setCity(String city);
}
