package com.reservation.controller.implementation;

import com.reservation.controller.IReservationController;
import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;

import com.reservation.service.IReservationService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class ReservationController implements IReservationController {

    @Inject
    private IReservationService reservationService;

    @Override
    public IReservation getReservation(Long id, boolean isDetailsRequired) throws Exception {
        return reservationService.getReservation(id, isDetailsRequired);
    }

    @Override
    public IReservation reservationRequest( IReservation reservation) throws Exception {
        return reservationService.requestForReservation(reservation);
    }

    @Override
    public IReservation updateReservation(IReservation reservation) throws ReservationEntityNotFoundException {
        return reservationService.updateReservation(reservation);
    }

    @Override
    public IGuest getReservationsByGuestId(Long guestId) throws Exception {
        return reservationService.getReservationsByGuestId(guestId);
    }
}
