package com.guest.controller;

import com.guest.model.IGuest;
import com.guest.model.IStay;
import com.guest.service.IGuestService;
import com.guest.service.implementation.GuestService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<IGuest> addNewGuest(@RequestBody IGuest guest) {
        IGuest response = service.addNewGuest(guest);
        return ResponseEntity.created(URI.create(String.format("/guest/" + response.getGuestId())))
                .body(response);
    }

    @RequestMapping(value = "/{guestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IGuest> getGuest(@PathVariable("guestId") Long guestId) {
        IGuest guest = service.getGuest(guestId, true);
        return ResponseEntity.ok(guest);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IGuest> getGuest(@RequestParam("guestId") Long guestId, @RequestParam("isHotelInfoRequired") boolean
    isToFetchHotelInfo) {
        IGuest guest = service.getGuest(guestId, isToFetchHotelInfo);
        return ResponseEntity.ok(guest);
    }

    @RequestMapping(value = "/addNewStay", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStayByGuest(@RequestParam("guestId") Long guestId, @RequestParam("reservationId") Long stay){
        return ResponseEntity.ok(service.addStayByGuest(guestId, stay));
    }

    @RequestMapping(value = "/getGuests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IGuest>> getGuests(List<Long> guestIds){
        return ResponseEntity.ok(service.getGuests(guestIds));
    }
}
