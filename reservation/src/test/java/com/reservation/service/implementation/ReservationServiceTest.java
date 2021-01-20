package com.reservation.service.implementation;


import com.reservation.entity.CardEntity;
import com.reservation.entity.ReservationEntity;
import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.model.ICard;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.Card;
import com.reservation.model.implementation.Reservation;
import com.reservation.model.implementation.ReservationStatus;
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

import static org.junit.Assert.assertFalse;
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
    private IReservation reservation = new Reservation(new Date(), new Date(), 1L, 1L, 1L, ReservationStatus.REQUEST,"SINGLE", card);
    private ReservationEntity reservationEntity =  new ReservationEntity(new Date(), new Date(), 1L, 1L, 1L, "REQUEST", new CardEntity(card.getCardNumber(), card.getExpMonth(), card.getExpYear()));


    @Test
    public void getGuestById() throws Exception {
        given(guestProxy.getGuest(anyLong())).willReturn(ResponseEntity.ok(iGuest));
        IGuest guest = reservationService.getGuestById(1L);
        assertTrue(guest.getGuestId().equals(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void getGuestById_EntityNotFound() throws Exception {
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
    public void requestForReservation() throws Exception {
        given(reservationRepository.save(any())).willReturn(reservationEntity);
        given(hotelProxy.reservationRequest(any(), anyLong())).willReturn(ResponseEntity.accepted().build());
        reservationService.requestForReservation(reservation);
        verify(hotelProxy).reservationRequest(any(), anyLong());
    }

    @Test
    public void confirmReservation() throws ReservationEntityNotFoundException {
        given(hotelProxy.updateReservation(anyLong(),any())).willReturn(ResponseEntity.accepted().build());
        given(guestProxy.addStayByGuest(anyLong(), anyLong())).willReturn(ResponseEntity.ok(iGuest));
        given(guestProxy.addNewCard(anyLong(),any())).willReturn(ResponseEntity.ok(iGuest));
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationEntity);
        given(paymentProxy.doPayment(any(), anyDouble())).willReturn("SUCCESS");
        reservation.setState(ReservationStatus.CONFIRM);
        IReservation reservation = reservationService.updateReservation(this.reservation);
        verify(hotelProxy).updateReservation(anyLong(),any());
        verify(guestProxy).addStayByGuest(anyLong(), anyLong());
        verify(reservationRepository).getReservationById(anyLong());
        verify(paymentProxy).doPayment(any(),anyDouble());

        assertTrue(reservation.getState().equals(ReservationStatus.CONFIRM));
    }

    @Test
    public void doPayment() {
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationEntity);
        given(paymentProxy.doPayment(any(), anyDouble())).willReturn("SUCCESS");
        reservationService.doPayment(card, 500, 1L);
        verify(paymentProxy).doPayment(any(), anyDouble());
    }

    @Test
    public void getReservation() throws Exception {
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationEntity);
        reservationService.getReservation(1L, false);
        verify(hotelProxy,times(0)).getHotelById(anyLong());
        verify(guestProxy, times(0)).getGuest(anyLong());
    }

    @Test
    public void getReservation_GetDetails() throws Exception {
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationEntity);
        given(guestProxy.getGuest(anyLong())).willReturn(ResponseEntity.ok(iGuest));
        given(hotelProxy.getHotelById(anyLong())).willReturn(iHotel);
        reservationService.getReservation(1L, true);
        verify(hotelProxy).getHotelById(anyLong());
        verify(guestProxy).getGuest(anyLong());
    }


    @Test
    public void getReservationsByGuestId() {
        iGuest.getReservations().add(1l);
        given(guestProxy.getGuest(anyLong())).willReturn(ResponseEntity.ok(iGuest));
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationEntity);
        com.reservation.model.IGuest reservationsByGuestId = reservationService.getReservationsByGuestId(1l);

        assertFalse(reservationsByGuestId.getReservations().isEmpty());
        assertTrue(reservationsByGuestId.getReservations().stream().filter(r -> r.getReservationId().equals(1l)).findFirst().isPresent());

    }

    @Test
    public void cancelReservation() throws Exception, ReservationEntityNotFoundException {
        CardEntity cardEntity =  new CardEntity(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        reservationEntity.setCard(cardEntity);
        given(reservationRepository.getReservationById(anyLong())).willReturn(reservationEntity);
        given(hotelProxy.updateReservation(anyLong(), any())).willReturn(ResponseEntity.accepted().build());
        given(paymentProxy.revertPayment(any(), anyDouble())).willReturn("SUCCESS");
        reservation.setState(ReservationStatus.CANCELLED);
        reservationService.updateReservation(reservation);
        verify(reservationRepository).getReservationById(anyLong());
        verify(hotelProxy).updateReservation(anyLong(), any());
        verify(paymentProxy).revertPayment(any(), anyDouble());
    }

}