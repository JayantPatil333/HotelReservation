package com.hotel.dto;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class AddressDTO {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long addressId;
        private String street;
        private String area;
        private String city;

    public AddressDTO(){}

        public String getStreet() {
        return street;
    }

        public Long getAddressId() {
        return addressId;
    }

        public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

        public void setStreet(String street) {
        this.street = street;
    }

        public String getArea() {
        return area;
    }

        public void setArea(String area) {
        this.area = area;
    }

        public String getCity() {
        return city;
    }

        public void setCity(String city) {
        this.city = city;
    }

    public AddressDTO(String street, String area, String city) {
        this.street = street;
        this.area = area;
        this.city = city;
    }

        @Override
        public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

}
