package com.guest.dto;

import com.guest.model.IHistory;
import com.guest.model.IStay;

import java.util.List;

public class HistoryDTO {
    private List<StayDTO> stayList;
    private List<StayDTO> cancelledStay;

    public List<StayDTO> getStayList() {
        return stayList;
    }

    public void setStayList(List<StayDTO> stayList) {
        this.stayList = stayList;
    }

    public List<StayDTO> getCancelledStay() {
        return cancelledStay;
    }

    public void setCancelledStay(List<StayDTO> cancelledStay) {
        this.cancelledStay = cancelledStay;
    }
}
