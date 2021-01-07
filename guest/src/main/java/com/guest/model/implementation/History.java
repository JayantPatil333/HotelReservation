package com.guest.model.implementation;

import com.guest.model.IHistory;
import com.guest.model.IStay;

import java.util.List;

public class History implements IHistory {
    private List<IStay> stayList;
    private List<IStay> cancelledStay;

    public List<IStay> getStayList() {
        return stayList;
    }

    public void setStayList(List<IStay> stayList) {
        this.stayList = stayList;
    }

    public List<IStay> getCancelledStay() {
        return cancelledStay;
    }

    public void setCancelledStay(List<IStay> cancelledStay) {
        this.cancelledStay = cancelledStay;
    }
}
