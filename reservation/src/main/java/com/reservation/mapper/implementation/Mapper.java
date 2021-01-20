package com.reservation.mapper.implementation;

import com.reservation.entity.CardEntity;
import com.reservation.entity.ReservationEntity;
import com.reservation.mapper.IMapper;
import com.reservation.model.ICard;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.Guest;
import com.reservation.model.implementation.Reservation;
import com.reservation.model.implementation.ReservationStatus;
import com.reservation.proxy.model.payment.implementation.Card;

public class Mapper implements  IMapper {
    public ReservationEntity mapIReservationToReservationDTO(IReservation reservation){
        ReservationEntity reservationEntity =  new ReservationEntity();
        reservationEntity.setReservationId(reservation.getReservationId());
        reservationEntity.setFromDate(reservation.getFromDate());
        reservationEntity.setGuestId(reservation.getGuestId());
        reservationEntity.setState(reservation.getState().toString());
        reservationEntity.setHotelId(reservation.getHotelId());
        reservationEntity.setToDate(reservation.getToDate());
        reservationEntity.setCard(reservation.getCard()!= null ? mapICardToCardDTO(reservation.getCard()): null);
        reservationEntity.setAmount(reservation.getAmount());
        return reservationEntity;
    }

    public IReservation mapReservationDTOToIReservation(ReservationEntity reservationEntity){
        IReservation reservation =  new Reservation();
        reservation.setReservationId(reservationEntity.getReservationId());
        reservation.setFromDate(reservationEntity.getFromDate());
        reservation.setGuestId(reservationEntity.getGuestId());
        reservation.setHotelId(reservationEntity.getHotelId());
        reservation.setState(ReservationStatus.valueOf(reservationEntity.getState()));
        reservation.setToDate(reservationEntity.getToDate());
        reservation.setCard(reservationEntity.getCard() != null ? mapCardDTOToICard(reservationEntity.getCard()): null);
        reservation.setAmount(reservationEntity.getAmount());
        return reservation;
    }

    private ICard mapCardDTOToICard(CardEntity card) {
        ICard iCard =  new com.reservation.model.implementation.Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return iCard;
    }

    @Override
    public CardEntity mapICardToCardDTO(ICard card) {
        CardEntity cardEntity =  new CardEntity(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return cardEntity;
    }

    @Override
    public com.reservation.proxy.model.payment.ICard mapICardToProxy(ICard card) {
        com.reservation.proxy.model.payment.ICard proxyCard =  new Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return proxyCard;
    }

    @Override
    public ICard cardDTOToICard(CardEntity card) {
        ICard iCard =  new com.reservation.model.implementation.Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return iCard;
    }

    @Override
    public IGuest mapProxyGuestToIGuest(com.reservation.proxy.model.guest.IGuest proxyGuest) {
        IGuest iGuest =  new Guest();
        iGuest.setGuestId(proxyGuest.getGuestId());
        iGuest.setContactNumber(proxyGuest.getContactNumber());
        iGuest.setEmail(proxyGuest.getEmail());
        iGuest.setName(proxyGuest.getName());
        return iGuest;
    }
}
