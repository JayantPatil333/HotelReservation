package com.reservation.controller;

import com.reservation.model.IReservation;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;
import com.reservation.proxy.model.payment.ICard;
import com.reservation.service.IReservationService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Inject
    private IReservationService reservationService;

    @RequestMapping(value = "/guest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public IGuest getGuestById(@RequestParam("guestId") Long guestId){
        return reservationService.getGuestById(guestId);
    }

    @RequestMapping(value = "/hotel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public IHotel getHotelById(@RequestParam("hotelId") Long hotelId){
        return reservationService.getHotelById(hotelId);
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public String reservationRequest(@RequestBody IReservation reservation){
        return reservationService.requestForReservation(reservation);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public String confirmReservation(@RequestBody IReservation reservation)
    {
        return reservationService.confirmReservation(reservation);
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public String doPayment(@RequestBody ICard card,@RequestParam("amount") double amount){
        return reservationService.doPayment(card, amount);
    }

}
