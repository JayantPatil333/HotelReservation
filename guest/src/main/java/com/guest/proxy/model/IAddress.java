package com.guest.proxy.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.guest.proxy.model.implementation.Address;

@JsonDeserialize(as = Address.class)
public interface IAddress {
    public String getStreet();

    public Long getAddressId();

    public void setAddressId(Long addressId);

    public void setStreet(String street);

    public String getArea();

    public void setArea(String area);

    public String getCity();

    public void setCity(String city);

}
