package com.hotel.dto;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Hotel")
public class HotelDTO {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long hotelId;
        private String name;
        private String phoneNumber;
        private int starRatting;
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "addressId")
        private AddressDTO address;

        @ElementCollection
        @CollectionTable(name = "Room", joinColumns = @JoinColumn(name = "hotel_id"))
        @Column(name = "rooms")
        private List<RoomDTO> rooms = new ArrayList() ;

        @Column(name = "reservations")
        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "reservationId")
        private List<ReservationDTO> reservations = new ArrayList();

    private Map<Date, List<Long>> reservationsByDate =  new HashMap();
    public Map<Date, List<Long>> getReservationsByDate() {
        return reservationsByDate;
    }

    public void setReservationsByDate(Map<Date, List<Long>> reservationsByDate) {
        this.reservationsByDate = reservationsByDate;
    }
    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }
        public Long getHotelId() {
        return hotelId;
    }

        public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
        public String getName() {
        return name;
    }

        public void setName(String name) {
        this.name = name;
    }

        public String getPhoneNumber() {
        return phoneNumber;
    }

        public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

        public int getStarRatting() {
        return starRatting;
    }

        public void setStarRatting(int starRatting) {
        this.starRatting = starRatting;
    }

        public AddressDTO getAddress() {
        return address;
    }

        public void setAddress(AddressDTO address) {
        this.address = address;
    }

        public List<RoomDTO> getRooms() {
        return rooms;
    }

        public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

}
