package com.guest.dto;

import com.guest.model.IGuest;

import javax.persistence.*;

    @Entity
    @Table(name = "guest")
    @NamedQueries({
            @NamedQuery(name = "findGuestById", query = "from Guest g where g.guestId = :id")
    })
    public class GuestDTO {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long guestId;
        private String name;
        private String email;
        private String contactNumber;

        private HistoryDTO history;
        private int ratting;

        public Long getGuestId() {
            return guestId;
        }

        public void setGuestId(Long guestId) {
            this.guestId = guestId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public HistoryDTO getHistory() {
            return history;
        }

        public void setHistory(HistoryDTO history) {
            this.history = history;
        }

        public int getRatting() {
            return ratting;
        }

        public void setRatting(int ratting) {
            this.ratting = ratting;
        }
    }
