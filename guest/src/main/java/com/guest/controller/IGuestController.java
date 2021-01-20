package com.guest.controller;

import com.guest.model.ICard;
import com.guest.model.IGuest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "guestInformation", description = "APIs to store, retrieve and update Guest information")
@RequestMapping("/guests")
public interface IGuestController {

    @ApiOperation(value = "Add new Guest information.", response = IGuest.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Guest information successfully stored."),
            @ApiResponse(code = 401, message = "Authentication failed."),
            @ApiResponse(code = 403, message = "You are not authorized to do this operation."),
            @ApiResponse(code = 404, message = "Resource not found.")

    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IGuest> addNewGuest(@RequestBody IGuest guest);

    @ApiOperation(value = "Get guest information.")
    @GetMapping(value = "/{guestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IGuest> getGuest(@PathVariable("guestId") Long guestId);

    @ApiOperation(value = "Add stay for Guest.")
    @PutMapping(value = "/{guestId}/stay", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IGuest> addStayByGuest(@PathVariable("guestId") Long guestId, @RequestParam("reservationId") Long stay);

    @ApiOperation(value = "Get guests information for given list input.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IGuest>> getGuests(@RequestParam("guestId") List<Long> guestIds);

    @ApiOperation(value = "Add card for guest.")
    @PreAuthorize("hasRole('GUEST')")
    @PostMapping(value = "/{guestId}/card", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IGuest> addNewCard(@PathVariable("guestId") Long guestId,@RequestBody ICard card);
}
