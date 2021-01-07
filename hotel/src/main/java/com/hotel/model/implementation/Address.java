package com.hotel.model.implementation;
import com.hotel.model.IAddress;
public class Address implements IAddress {

    private Long addressId;
    private String street;
    private String area;
    private String city;

    public Address(){}

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

    /*public Address(String street, String area, String city) {
        this.street = street;
        this.area = area;
        this.city = city;
    }*/

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
