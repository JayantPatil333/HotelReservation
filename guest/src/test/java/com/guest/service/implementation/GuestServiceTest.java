package com.guest.service.implementation;

import com.guest.dto.CardDTO;
import com.guest.dto.GuestDTO;
import com.guest.model.ICard;
import com.guest.model.IGuest;
import com.guest.model.implementation.Card;
import com.guest.model.implementation.Guest;
import com.guest.repository.implementation.GuestRepository;
import com.guest.service.IGuestService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.Arrays;
import java.util.List;

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
    GuestDTO guestDTO1 =  new GuestDTO(2L, "Jayant","jayant@gmail.com", "1234567");
    @Test
    @Order(1)
    public void addNewGuest() {

        given(repository.save(any())).willReturn(guestDTO);
        IGuest resultGuest = guestService.addNewGuest(guest);

        assertEquals(guestDTO.getGuestId(), resultGuest.getGuestId());
        assertEquals(guestDTO.getName(), resultGuest.getName());
        assertEquals(guestDTO.getEmail(), resultGuest.getEmail());
        assertEquals(guestDTO.getContactNumber(), resultGuest.getContactNumber());
    }

    @Test
    @Order(2)
    public void getGuest() {
        given(repository.findById(anyLong())).willReturn(guestDTO);
        IGuest resultGuest = guestService.getGuest(1L);
        assertEquals(guestDTO.getGuestId(), resultGuest.getGuestId());
        assertEquals(guestDTO.getName(), resultGuest.getName());
        assertEquals(guestDTO.getEmail(), resultGuest.getEmail());
        assertEquals(guestDTO.getContactNumber(), resultGuest.getContactNumber());
    }

    @Test(expected = EntityNotFoundException.class)
    @Order(3)
    public void getGuest_EntityNotFound() {
        given(repository.findById(anyLong())).willThrow(new EntityNotFoundException("Entity not found"));
        guestService.getGuest(1L);
    }

    @Test
    @Order(4)
    public void addStayByGuest() {
        given(repository.findById(anyLong())).willReturn(guestDTO);
        IGuest resultGuest = guestService.addStayByGuest(1L, 1L);
        assertTrue(resultGuest.getReservations().stream().filter(aLong -> aLong.equals(1L)).findFirst().isPresent());
    }

    @Test
    @Order(5)
    public void getGuests() {
        given(repository.findById(1L)).willReturn(guestDTO);
        given(repository.findById(2L)).willReturn(guestDTO1);
        List<IGuest> guestList = guestService.getGuests(Arrays.asList(1L, 2L));
        assertTrue(guestList.stream().filter(iGuest -> iGuest.getGuestId().equals(1L)).findFirst().isPresent());
        assertTrue(guestList.stream().filter(iGuest -> iGuest.getGuestId().equals(2L)).findFirst().isPresent());
    }

    @Test
    @Order(6)
    public void addNewCard(){
        CardDTO card =  new CardDTO("1234567890765", "12","2014");
        guestDTO.getCards().add(card);
        ICard iCard =  new Card("1234567890765", "12","2014");
        given(repository.findById(1L)).willReturn(guestDTO);
        IGuest iGuest = guestService.addNewCard(1L, iCard);
        assertTrue(iGuest.getCards().stream().filter(c -> c.getCardNumber().equals(iCard.getCardNumber())).findFirst().isPresent());
    }
}