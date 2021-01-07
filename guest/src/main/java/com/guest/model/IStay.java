package com.guest.model;

import java.util.Date;

public interface IStay {
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
