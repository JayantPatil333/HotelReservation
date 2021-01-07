package com.guest.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.guest.model.implementation.Stay;

import java.util.Date;

@JsonDeserialize(as = Stay.class)
public interface IStay {

    public Long getHotelId();

    public void setHotelId(Long hotelId);

    public Long getStayId();

    public void setStayId(Long stayId);

    public boolean isCancelled();

    public void setCancelled(boolean cancelled);

    public String getReasonToCancel();

    public void setReasonToCancel(String reasonToCancel);

    public IHotel getHotel();

    public void setHotel(IHotel hotel);

    public Date getFromDate();

    public void setFromDate(Date fromDate);

    public Date getToDate();

    public void setToDate(Date toDate);

    public String getPaidBy();

    public void setPaidBy(String paidBy);
}
