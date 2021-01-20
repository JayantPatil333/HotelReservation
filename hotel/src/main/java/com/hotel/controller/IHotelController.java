package com.hotel.controller;

import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.Hotel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Store, retrieve and update Hotel information.")
public interface IHotelController {

    @ApiOperation(value = "Add new Hotel.")
    @PostMapping(value = "/hotels",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<IHotel> addNewHotel(@RequestBody Hotel hotel);

    @ApiOperation(value = "Attach reservation request with hotel.")
    @PostMapping(value = "/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IReservation> reservationRequest(@RequestBody IReservation reservation, @PathVariable("hotelId") Long hotelId);

    @ApiOperation(value = "Update reservation.")
    @PutMapping(value = "/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IReservation> updateReservation(@PathVariable("hotelId") Long hotelId,@RequestBody IReservation reservation);

    @ApiOperation(value = "Get all reservations by hotel id.")
    @GetMapping(value = "/hotels/{hotelId}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<List<IReservation>> getAllReservationsByHotelId(@PathVariable("hotelId") Long hotelId);

    @ApiOperation(value = "Get all reservations for guest by hotel id.")
    @GetMapping(value = "/hotels/{hotelId}/{guestId}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public List<IReservation> getReservationByGuest(@PathVariable("hotelId") Long hotelId,@PathVariable("guestId") Long guestId);

    @ApiOperation(value = "Retrieve hotels information provided by input list.")
    @GetMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IHotel> getHotels(@RequestParam("hotelIds") List<Long> hotelIds);

    @ApiOperation(value = "Get hotel information by hotel id.")
    @GetMapping(value = "/hotels/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('HOTEL', 'GUEST')")
    public IHotel getHotelById(@PathVariable("hotelId") Long hotelId);

    @ApiOperation(value = "Search hotels per City.")
    @GetMapping(value = "/{cityName}/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public List<IHotel> searchHotels(@PathVariable("cityName") String cityName );//, @RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("roomType") String roomType) throws ParseException;
}
