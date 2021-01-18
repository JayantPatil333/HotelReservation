package com.reservation.mapper.implementation;

import com.reservation.dto.CardDTO;
import com.reservation.dto.ReservationDTO;
import com.reservation.mapper.IMapper;
import com.reservation.model.ICard;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.Reservation;
import com.reservation.proxy.model.payment.implementation.Card;

public class Mapper implements  IMapper {
    public ReservationDTO mapIReservationToReservationDTO(IReservation reservation){
        ReservationDTO reservationDTO =  new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setFromDate(reservation.getFromDate());
        reservationDTO.setGuestId(reservation.getGuestId());
        reservationDTO.setState(reservation.getState());
        reservationDTO.setHotelId(reservation.getHotelId());
        reservationDTO.setToDate(reservation.getToDate());
        reservationDTO.setCard(mapICardToCardDTO(reservation.getCard()));
        return reservationDTO;
    }

    public IReservation mapReservationDTOToIReservation(ReservationDTO reservationDTO){
        IReservation reservation =  new Reservation();
        reservation.setReservationId(reservationDTO.getReservationId());
        reservation.setFromDate(reservationDTO.getFromDate());
        reservation.setGuestId(reservationDTO.getGuestId());
        reservation.setHotelId(reservationDTO.getHotelId());
        reservation.setState(reservationDTO.getState());
        reservation.setToDate(reservationDTO.getToDate());
        reservation.setCard(mapCardDTOToICard(reservationDTO.getCard()));
        return reservation;
    }

    private ICard mapCardDTOToICard(CardDTO card) {
        ICard iCard =  new com.reservation.model.implementation.Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return iCard;
    }

    @Override
    public CardDTO mapICardToCardDTO(ICard card) {
        CardDTO cardDTO =  new CardDTO(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return cardDTO;
    }

    @Override
    public com.reservation.proxy.model.payment.ICard mapICardToProxy(ICard card) {
        com.reservation.proxy.model.payment.ICard proxyCard =  new Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return proxyCard;
    }

    @Override
    public ICard cardDTOToICard(CardDTO card) {
        ICard iCard =  new com.reservation.model.implementation.Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return iCard;
    }
}
