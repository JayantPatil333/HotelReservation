package com.guest.model;

public interface IGuest {

    public Long getGuestId();

    public void setGuestId(Long guestId);

    public String getName();

    public void setName(String name);

    public String getEmail();

    public void setEmail(String email);

    public String getContactNumber();

    public void setContactNumber(String contactNumber);

    public IHistory getHistory() ;

    public void setHistory(IHistory history) ;

    public int getRatting();

    public void setRatting(int ratting);
}
