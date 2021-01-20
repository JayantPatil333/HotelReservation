package com.reservation.proxy;

import com.reservation.model.IReservation;
import com.reservation.proxy.model.hotel.IHotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ZuulApiGateway")
public interface IHotelInformationProxy {
    @GetMapping(value = "/HotelService/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IHotel> getHotels(@RequestParam("hotelIds") List<Long> hotelIds);

    @GetMapping(value = "/HotelService/hotels/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IHotel getHotelById(@PathVariable("hotelId") Long hotelId);

    @PostMapping(value = "/HotelService/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IReservation> reservationRequest(@RequestBody IReservation reservation, @PathVariable("hotelId") Long hotelId);

    @PutMapping(value = "/HotelService/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IReservation> updateReservation(@PathVariable("hotelId") Long hotelId,@RequestBody IReservation reservation);
}
