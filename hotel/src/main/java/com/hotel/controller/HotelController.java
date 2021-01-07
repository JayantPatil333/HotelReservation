package com.hotel.controller;

import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.Hotel;
import com.hotel.services.IHotelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController{

    @Inject
    private IHotelService service;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IHotel> addNewHotel(@RequestBody Hotel hotel)
    {
        IHotel response = service.addNewHotel(hotel);
        URI uri = URI.create(String.format("/hotel/%s", response.getHotelId()));
        return ResponseEntity.created(uri).body(response);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IHotel>> getHotelsByCityDateRange(@RequestParam String cityName, @RequestParam Date fromDate, @RequestParam Date toDate){
        List<IHotel> hotels = service.getHotelsByCityAndDateRange(cityName, fromDate,toDate);
        return ResponseEntity.ok(hotels);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> reservationRequest(@RequestBody IReservation reservation, @RequestParam("hotelId") Long hotelId){
        return ResponseEntity.accepted().body(service.reservationRequest(hotelId, reservation)) ;
    }

    @RequestMapping(value = "/cancelReservation", method = RequestMethod.PATCH)
    public ResponseEntity<String> cancelReservation(@RequestParam("hotelId") Long hotelId,@RequestParam("reservationId") Long reservationId){
        return ResponseEntity.accepted().body(service.cancelReservation(hotelId, reservationId));
    }

    @RequestMapping(value = "/reservationsByHotel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IReservation>> getAllReservationsByHotelId(@RequestParam("hotelId") Long hotelId){
        return ResponseEntity.ok(service.getAllReservationsByHotelId(hotelId));
    }

    @RequestMapping(value = "/reservationsByGuest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IReservation>> getReservationByGuest(@RequestParam("hotelId") Long hotelId,@RequestParam("guestId") Long guestId)
    {
        List<IReservation> reservations = service.getReservationByGuestIdPerHotel(hotelId, guestId);
        return ResponseEntity.ok(reservations);
    }

    @RequestMapping(value = "/confirmReservation" , method = RequestMethod.PATCH)
    public ResponseEntity<String> confirmReservation(@RequestParam("reservationId") Long reservationId){
        return ResponseEntity.ok(service.confirmReservation(reservationId));
    }

}