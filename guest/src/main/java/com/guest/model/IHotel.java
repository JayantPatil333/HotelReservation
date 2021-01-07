package com.guest.model;

public interface IHotel {

    public String getName() ;

    public void setName(String name) ;

    public String getContactNumber();

    public void setContactNumber(String contactNumber);

    public IAddress getAddress() ;

    public void setAddress(IAddress address);
}
