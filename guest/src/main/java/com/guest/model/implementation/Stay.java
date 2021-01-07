package com.guest.model.implementation;

import com.guest.model.IHotel;
import com.guest.model.IStay;

import java.util.Date;

public class Stay implements IStay {
    private IHotel hotel;
    private Date fromDate;
    private Date toDate;
    private String paidBy;
    private boolean isCancelled;
    private String reasonToCancel;

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public String getReasonToCancel() {
        return reasonToCancel;
    }

    public void setReasonToCancel(String reasonToCancel) {
        this.reasonToCancel = reasonToCancel;
    }

    public IHotel getHotel() {
        return hotel;
    }

    public void setHotel(IHotel hotel) {
        this.hotel = hotel;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }
}
