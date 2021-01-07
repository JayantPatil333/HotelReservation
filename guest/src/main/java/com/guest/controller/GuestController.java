package com.guest.controller;
import com.guest.dto.GuestDTO;
import com.guest.model.IGuest;
import com.guest.service.GuestService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;

@RestController
@RequestMapping("/guest")
public class GuestController {
    @Inject
    GuestService service;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IGuest> addNewGuest(@RequestBody IGuest guest){
        IGuest response = service.addNewGuest(guest);
        return ResponseEntity.created(URI.create(String.format("/guest/"+response.getGuestId())))
        .body(response);
    }

    @RequestMapping(value = "/{guestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IGuest> getGuest(@PathVariable("guestId") Long guestId){
        IGuest guest = service.getGuest(guestId);
        return ResponseEntity.ok(guest);
    }
}
