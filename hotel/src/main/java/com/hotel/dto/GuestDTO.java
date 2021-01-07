package com.hotel.dto;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Table(name = "Guest")
public class GuestDTO {

        private Long guestId;
        private String name;
        private String contactNumber;

    public GuestDTO(){}
        public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

        public Long getGuestId() {
        return guestId;
    }

        public String getName() {
        return name;
    }

        public void setName(String name) {
        this.name = name;
    }

        public String getContactNumber() {
        return contactNumber;
    }

        public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
