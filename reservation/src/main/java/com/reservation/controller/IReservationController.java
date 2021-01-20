package com.reservation.controller;

import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.model.ICard;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api("APIs to store, retrieve and update reservation information.")
@RequestMapping("/reservations")
public interface IReservationController {

    @ApiOperation(value = "To get reservation information by id.")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IReservation getReservation(@PathVariable("id") Long id, @RequestParam("isDetailsRequired") boolean isDetailsRequired) throws Exception;

    @ApiOperation(value = "Create new reservation.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public IReservation reservationRequest(@RequestBody IReservation reservation) throws Exception;

    @ApiOperation(value = "Update reservation.")
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public IReservation updateReservation(@RequestBody IReservation reservation) throws ReservationEntityNotFoundException;

    @ApiOperation(value = "Get reservation history for Guest")
    @GetMapping(value = "/guests/{guestId}")
    @PreAuthorize("hasRole('GUEST')")
    public IGuest getReservationsByGuestId(@PathVariable("guestId") Long guestId) throws Exception;

}
