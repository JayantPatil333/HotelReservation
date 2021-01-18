package com.reservation.mapper;

import com.reservation.dto.CardDTO;
import com.reservation.dto.ReservationDTO;
import com.reservation.model.ICard;
import com.reservation.model.IReservation;

public interface IMapper {

    public ReservationDTO mapIReservationToReservationDTO(IReservation reservation);

    public IReservation mapReservationDTOToIReservation(ReservationDTO reservationDTO);

    public CardDTO mapICardToCardDTO(ICard card);

    com.reservation.proxy.model.payment.ICard mapICardToProxy(ICard card);

    public ICard cardDTOToICard(CardDTO card);
}
