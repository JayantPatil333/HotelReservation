package com.hotel.controller;

import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.Hotel;
import com.hotel.services.IHotelService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/hotel")
public class HotelController{

    @Inject
    private IHotelService service;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<IHotel> addNewHotel(@RequestBody Hotel hotel)
    {
        IHotel response = service.addNewHotel(hotel);
        URI uri = URI.create(String.format("/hotel/%s", response.getHotelId()));
        return ResponseEntity.created(uri).body(response);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IReservation> reservationRequest(@RequestBody IReservation reservation, @RequestParam("hotelId") Long hotelId){
        return ResponseEntity.ok(service.reservationRequest(hotelId, reservation)) ;
    }

    @RequestMapping(value = "/cancelReservation", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IReservation> cancelReservation(@RequestParam("hotelId") Long hotelId,@RequestParam("reservationId") Long reservationId){
        return ResponseEntity.accepted().body(service.cancelReservation(hotelId, reservationId));
    }

    @RequestMapping(value = "/reservationsByHotel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<List<IReservation>> getAllReservationsByHotelId(@RequestParam("hotelId") Long hotelId){
        return ResponseEntity.ok(service.getAllReservationsByHotelId(hotelId));
    }

    @RequestMapping(value = "/reservationsByGuest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public List<IReservation> getReservationByGuest(@RequestParam("hotelId") Long hotelId,@RequestParam("guestId") Long guestId)
    {
        List<IReservation> reservations = service.getReservationByGuestIdPerHotel(hotelId, guestId);
        return reservations;
    }

    @RequestMapping(value = "/confirmReservation" , method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IReservation> confirmReservation(@RequestParam("reservationId") Long reservationId){
        return ResponseEntity.accepted().body(service.confirmReservation(reservationId));
    }

    @RequestMapping(value = "/getHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IHotel> getHotels(@RequestParam("hotelIds") List<Long> hotelIds){
        return service.getHotels(hotelIds);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public IHotel getHotelById(@RequestParam("hotelId") Long hotelId){
        return service.getHotelById(hotelId);
    }


    @RequestMapping(value = "/searchHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public List<IHotel> searchHotels(@RequestParam("cityName") String cityName, @RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("roomType") String roomType) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date fromDate = format.parse(from);
        Date toDate = format.parse(to);
        List<IHotel> hotels = service.searchHotels(cityName, fromDate, toDate, roomType);
        return hotels;
    }
}