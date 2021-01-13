package com.guest.service.implementation;

import com.guest.dto.GuestDTO;
import com.guest.model.IGuest;
import com.guest.model.implementation.Guest;
import com.guest.repository.implementation.GuestRepository;
import com.guest.service.IGuestService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceTest {

    @Autowired
    private IGuestService guestService;

    @MockBean
    private GuestRepository repository;

    IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
    GuestDTO guestDTO =  new GuestDTO(1L, "Jayant","jayant@gmail.com", "1234567");
    @Test
    public void addNewGuest() {

        given(repository.save(any())).willReturn(guestDTO);
        IGuest resultGuest = guestService.addNewGuest(guest);

        assertEquals(guestDTO.getGuestId(), resultGuest.getGuestId());
        assertEquals(guestDTO.getName(), resultGuest.getName());
        assertEquals(guestDTO.getEmail(), resultGuest.getEmail());
        assertEquals(guestDTO.getContactNumber(), resultGuest.getContactNumber());
    }

    @Test
    public void getGuest() {
        given(repository.findById(anyLong())).willReturn(guestDTO);
        IGuest resultGuest = guestService.getGuest(1L);
        assertEquals(guestDTO.getGuestId(), resultGuest.getGuestId());
        assertEquals(guestDTO.getName(), resultGuest.getName());
        assertEquals(guestDTO.getEmail(), resultGuest.getEmail());
        assertEquals(guestDTO.getContactNumber(), resultGuest.getContactNumber());
    }

    @Test(expected = EntityNotFoundException.class)
    public void getGuest_EntityNotFound() {
        given(repository.findById(anyLong())).willThrow(new EntityNotFoundException("Entity not found"));
        guestService.getGuest(1L);
    }

    @Test
    public void addStayByGuest() {
    }

    @Test
    public void getGuests() {
    }
}