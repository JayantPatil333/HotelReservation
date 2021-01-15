package com.guest.controller;

import com.guest.model.ICard;
import com.guest.model.IGuest;
import com.guest.service.IGuestService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {
    @Inject
    private IGuestService service;


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IGuest> addNewGuest(@RequestBody IGuest guest) {
        IGuest response = service.addNewGuest(guest);
        return ResponseEntity.created(URI.create(String.format("/guest/" + response.getGuestId())))
                .body(response);
    }

    @RequestMapping(value = "/{guestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IGuest> getGuest(@PathVariable("guestId") Long guestId) {
        IGuest guest = service.getGuest(guestId);
        return ResponseEntity.ok(guest);
    }

    @RequestMapping(value = "/addNewStay", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<IGuest> addStayByGuest(@RequestParam("guestId") Long guestId, @RequestParam("reservationId") Long stay){
        return ResponseEntity.ok(service.addStayByGuest(guestId, stay));
    }

    @RequestMapping(value = "/getGuests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IGuest>> getGuests(@RequestParam("guestId") List<Long> guestIds){
        return ResponseEntity.ok(service.getGuests(guestIds));
    }


    @PreAuthorize("hasRole('GUEST')")
    @RequestMapping(value = "/addNewCard", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IGuest> addNewCard(@RequestParam("guestId") Long guestId,@RequestBody ICard card){
        return ResponseEntity.ok(service.addNewCard(guestId, card));
    }
}
