package com.hotel.controller.implementation;

import com.hotel.controller.IHotelController;
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
public class HotelController implements IHotelController {

    @Inject
    private IHotelService service;

    @Override
    public ResponseEntity<IHotel> addNewHotel(Hotel hotel)
    {
        IHotel response = service.addNewHotel(hotel);
        URI uri = URI.create(String.format("/hotel/%s", response.getHotelId()));
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    public ResponseEntity<IReservation> reservationRequest(IReservation reservation, Long hotelId){
        return ResponseEntity.ok(service.reservationRequest(hotelId, reservation)) ;
    }

    @Override
    public ResponseEntity<IReservation> updateReservation(Long hotelId, IReservation reservation) {
        IReservation reservationResult = service.updateReservation(hotelId, reservation);
        return ResponseEntity.accepted().body(reservationResult);
    }

    @Override
    public ResponseEntity<List<IReservation>> getAllReservationsByHotelId(Long hotelId){
        return ResponseEntity.ok(service.getAllReservationsByHotelId(hotelId));
    }

    @Override
    public List<IReservation> getReservationByGuest(Long hotelId, Long guestId)
    {
        List<IReservation> reservations = service.getReservationByGuestIdPerHotel(hotelId, guestId);
        return reservations;
    }

    @Override
    public List<IHotel> getHotels(List<Long> hotelIds){
        return service.getHotels(hotelIds);
    }

    @Override
    public IHotel getHotelById(Long hotelId){
        return service.getHotelById(hotelId);
    }

    @Override
    public List<IHotel> searchHotels(String cityName) {
        List<IHotel> hotels = service.searchHotelsByCity(cityName);
        return hotels;
    }

    /*@Override
    public List<IHotel> searchHotels(@RequestParam("cityName") String cityName, @RequestParam("fromDate") String from, @RequestParam("toDate") String to, @RequestParam("roomType") String roomType) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date fromDate = format.parse(from);
        Date toDate = format.parse(to);
        List<IHotel> hotels = service.searchHotels(cityName, fromDate, toDate, roomType);
        return hotels;
    }*/
}