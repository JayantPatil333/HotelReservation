package com.guest.model;

import java.util.List;

public interface IHistory {
    public List<IStay> getStayList();

    public void setStayList(List<IStay> stayList);

    public List<IStay> getCancelledStay();

    public void setCancelledStay(List<IStay> cancelledStay);
}
