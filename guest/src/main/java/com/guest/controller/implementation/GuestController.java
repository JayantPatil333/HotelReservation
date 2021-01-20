package com.guest.controller.implementation;

import com.guest.controller.IGuestController;
import com.guest.model.ICard;
import com.guest.model.IGuest;
import com.guest.service.IGuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@RestController
public class GuestController implements IGuestController {
    @Inject
    private IGuestService service;

    @Override
    public ResponseEntity<IGuest> addNewGuest(IGuest guest) {
        IGuest response = service.addNewGuest(guest);
        return ResponseEntity.created(URI.create(String.format("/guest/" + response.getGuestId())))
                .body(response);
    }

    @Override
    public ResponseEntity<IGuest> getGuest(Long guestId) {
        IGuest guest = service.getGuest(guestId);
        return ResponseEntity.ok(guest);
    }

    @Override
    public ResponseEntity<IGuest> addStayByGuest(Long guestId, Long stay){
        return ResponseEntity.ok(service.addStayByGuest(guestId, stay));
    }

    @Override
    public ResponseEntity<List<IGuest>> getGuests(List<Long> guestIds){
        return ResponseEntity.ok(service.getGuests(guestIds));
    }

    @Override
    public ResponseEntity<IGuest> addNewCard(Long guestId,ICard card){
        return ResponseEntity.ok(service.addNewCard(guestId, card));
    }
}
