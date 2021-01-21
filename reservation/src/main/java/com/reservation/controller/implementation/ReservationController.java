package com.reservation.controller.implementation;

import com.reservation.controller.IReservationController;
import com.reservation.exception.ApiError;
import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;

import com.reservation.response.ApiResponseImpl;
import com.reservation.service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Reservation service Controller implementation.
 */
@RestController
public class ReservationController implements IReservationController {

    @Inject
    private IReservationService reservationService;

    @Override
    public ApiResponseImpl<IReservation> getReservation(Long id, boolean isDetailsRequired) throws ReservationEntityNotFoundException {
        IReservation reservation = reservationService.getReservation(id, isDetailsRequired);
        return generateApiResponse(reservation, HttpStatus.OK);
    }

    private <T> ApiResponseImpl<T> generateApiResponse(T actualData, HttpStatus status) {
        return new ApiResponseImpl(status, null, actualData);
    }

    @Override
    public ApiResponseImpl<IReservation> reservationRequest(IReservation reservation) {
        IReservation saved = reservationService.requestForReservation(reservation);
        return generateApiResponse(saved, HttpStatus.CREATED);

    }

    @Override
    public ApiResponseImpl<IReservation> updateReservation(IReservation reservation) throws ReservationEntityNotFoundException {
        IReservation update = reservationService.updateReservation(reservation);
        return generateApiResponse(update, HttpStatus.ACCEPTED);
    }

    @Override
    public ApiResponseImpl<IGuest> getReservationsByGuestId(Long guestId) throws ReservationEntityNotFoundException {
        IGuest guest = reservationService.getReservationsByGuestId(guestId);
        return generateApiResponse(guest, HttpStatus.OK);
    }
}
