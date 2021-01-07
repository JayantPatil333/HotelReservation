package com.hotel.model.implementation;

import com.hotel.model.IRoom;

import javax.persistence.*;
import java.util.Objects;

public class Room implements IRoom {

    private int roomNo;
    private double rentPerNight;
    private RoomType roomType;

    //private List<Date> occupiedDates = new ArrayList();
    private boolean isOccupiedCurrently = false;

    /*@JoinColumn(name = "guestId")
    private Guest guest;*/

    public Room(){}
    public boolean isOccupiedCurrently() {
        return isOccupiedCurrently;
    }

    public void setOccupiedCurrently(boolean occupiedCurrently) {
        isOccupiedCurrently = occupiedCurrently;
    }

  /*  public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        if(!this.isOccupiedCurrently)
            this.guest = guest;
    }*/

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public double getRentPerNight() {
        return rentPerNight;
    }

    public void setRentPerNight(double rentPerNight) {
        this.rentPerNight = rentPerNight;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

/*
    public List<Date> getOccupiedDates() {
        return occupiedDates;
    }

    public void setOccupiedDates(List<Date> occupiedDates) {
        this.occupiedDates = occupiedDates;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNo == room.roomNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNo);
    }
}
