package com.reservation.service.implementation;


import com.reservation.dto.CardDTO;
import com.reservation.dto.ReservationDTO;
import com.reservation.model.ICard;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.Card;
import com.reservation.model.implementation.Reservation;
import com.reservation.proxy.IGuestInformationProxy;
import com.reservation.proxy.IHotelInformationProxy;
import com.reservation.proxy.IPaymentServiceProxy;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.guest.implementation.Guest;
import com.reservation.proxy.model.hotel.IAddress;
import com.reservation.proxy.model.hotel.IHotel;
import com.reservation.proxy.model.hotel.implementation.Address;
import com.reservation.proxy.model.hotel.implementation.Hotel;

import com.reservation.repository.implementation.ReservationRepository;
import com.reservation.service.IReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private IHotelInformationProxy hotelProxy;

    @MockBean
    private IGuestInformationProxy guestProxy;

    @MockBean
    private IPaymentServiceProxy paymentProxy;

    @Autowired
    private IReservationService reservationService;

    private IGuest iGuest =  new Guest(1l, "Jayant","jayant@gmail.com","1234567899");
    private IAddress iAddress = new Address(1L, "Lane no 5", "Hanuman Nagar", "Pune");
    private IHotel iHotel = new Hotel(1L, "City Inn", "1234567890", 3, iAddress);;
    private ICard card =  new Card("1234567890","12","2024");
    private IReservation reservation = new Reservation(new Date(), new Date(), 1L, 1L, 1L, "REQUEST","SINGLE", card);
    private ReservationDTO reservationDTO =  new ReservationDTO(new Date(), new Date(), 1L, 1L, 1L, "REQUEST", new CardDTO(card.getCardNumber(), card.getExpMonth(), card.getExpYear()));


    @Test
    public void getGuestById() {
        given(guestProxy.getGuest(anyLong())).willReturn(iGuest);
        IGuest guest = reservationService.getGuestById(1L);
        assertTrue(guest.getGuestId().equals(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void getGuestById_EntityNotFound() {
        given(guestProxy.getGuest(anyLong())).willThrow(new EntityNotFoundException("Guest not found"));
        reservationService.getGuestById(1L);
    }

    @Test
    public void getHotelById() {
        given(hotelProxy.getHotelById(anyLong())).willReturn(iHotel);
        IHotel hotel = reservationService.getHotelById(1L);
        assertTrue(hotel.getHotelId().equals(1L));
        assertTrue(hotel.getAddress().getAddressId().equals(1L));
    }

    @Test
    public void requestForReservation() {
        given(reservationRepository.save(any())).willReturn(reservationDTO);
        given(hotelProxy.reservationRequest(any(), anyLong())).willReturn("Request submitted.");
        reservationService.requestForReservation(reservation);
        verify(hotelProxy).reservationRequest(any(), anyLong());
    }

    @Test
    public void confirmReservation() {
        given(hotelProxy.confirmReservation(anyLong())).willReturn("SUCCESS");
        given(guestProxy.addStayByGuest(anyLong(), anyLong())).willReturn(ResponseEntity.ok("SUCCESS"));
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        IReservation reservation = reservationService.confirmReservation(this.reservation);
        verify(hotelProxy).confirmReservation(anyLong());
        verify(guestProxy).addStayByGuest(anyLong(), anyLong());
        verify(reservationRepository).getReservationById(anyLong());

        assertTrue(reservation.getState().equals("CONFIRMED"));
    }

    @Test
    public void doPayment() {
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        given(paymentProxy.doPayment(any(), anyDouble())).willReturn("SUCCESS");
        reservationService.doPayment(card, 500, 1L);
        verify(paymentProxy).doPayment(any(), anyDouble());
    }

    @Test
    public void getReservation() {
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        reservationService.getReservation(1L, false);
        verify(hotelProxy,times(0)).getHotelById(anyLong());
        verify(guestProxy, times(0)).getGuest(anyLong());
    }

    @Test
    public void getReservation_GetDetails() {
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        given(guestProxy.getGuest(anyLong())).willReturn(iGuest);
        given(hotelProxy.getHotelById(anyLong())).willReturn(iHotel);
        reservationService.getReservation(1L, true);
        verify(hotelProxy).getHotelById(anyLong());
        verify(guestProxy).getGuest(anyLong());
    }

    @Test
    public void doPaymentFallBack() {
    }

    @Test
    public void cancelReservation() throws Exception {
        CardDTO cardDTO =  new CardDTO(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        reservationDTO.setCard(cardDTO);
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationDTO);
        given(hotelProxy.cancelReservation(anyLong(), anyLong())).willReturn(ResponseEntity.accepted().build());
        given(paymentProxy.revertPayment(any(), anyDouble())).willReturn("SUCCESS");

        reservationService.cancelReservation(1L, 500);

        verify(reservationRepository).getReservationById(anyLong());
        verify(hotelProxy).cancelReservation(anyLong(), anyLong());
        verify(paymentProxy).revertPayment(any(), anyDouble());
    }

}