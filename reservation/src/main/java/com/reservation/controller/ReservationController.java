package com.reservation.controller;

import com.reservation.model.ICard;
import com.reservation.model.IReservation;

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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public IReservation getReservation(@RequestParam("id") Long id, @RequestParam("isDetailsRequired") boolean isDetailsRequired){
        return reservationService.getReservation(id, isDetailsRequired);
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public String reservationRequest(@RequestBody IReservation reservation){
        return reservationService.requestForReservation(reservation);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public IReservation confirmReservation(@RequestBody IReservation reservation)
    {
        return reservationService.confirmReservation(reservation);
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public String doPayment(@RequestBody ICard card, @RequestParam("amount") double amount, @RequestParam("reservationId") Long reservationId){
        return reservationService.doPayment(card, amount, reservationId);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public IReservation cancelReservation(@RequestParam("reservationId") Long id, @RequestParam("amount") double amount) throws Exception {

        return reservationService.cancelReservation(id, amount);
    }

}
