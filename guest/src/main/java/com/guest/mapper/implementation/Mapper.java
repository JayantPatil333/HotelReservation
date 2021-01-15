package com.guest.mapper.implementation;

import com.guest.dto.*;
import com.guest.mapper.IMapper;
import com.guest.model.*;
import com.guest.model.implementation.*;

import java.util.stream.Collectors;

public class Mapper implements IMapper {
    @Override
    public IGuest mapGuestDTOToIGuest(GuestDTO guestDTO) {
        IGuest iGuest =  new Guest();
        iGuest.setGuestId(guestDTO.getGuestId());
        iGuest.setContactNumber(guestDTO.getContactNumber());
        iGuest.setEmail(guestDTO.getEmail());
        iGuest.setName(guestDTO.getName());
        iGuest.setRatting(guestDTO.getRatting());
        iGuest.setCards(guestDTO.getCards().stream().map(this::mapCardDTOToICard).collect(Collectors.toList()));
        iGuest.setReservations(guestDTO.getReservations());
        return iGuest;
    }

    @Override
    public GuestDTO mapIGuestToGuestDTO(IGuest iGuest) {
        GuestDTO guestDTO =  new GuestDTO();
        guestDTO.setGuestId(iGuest.getGuestId());
        guestDTO.setName(iGuest.getName());
        guestDTO.setEmail(iGuest.getEmail());
        guestDTO.setRatting(iGuest.getRatting());
        guestDTO.setContactNumber(iGuest.getContactNumber());
        guestDTO.setCards(iGuest.getCards().stream().map(this::mapICardToCardDTO).collect(Collectors.toList()));
        guestDTO.setReservations(iGuest.getReservations());
        return guestDTO;
    }

    public ICard mapCardDTOToICard(CardDTO cardDTO){
        ICard iCard = new Card();
        iCard.setCardNumber(cardDTO.getCardNumber());
        iCard.setExpMonth(cardDTO.getExpMonth());
        iCard.setExpYear(cardDTO.getExpYear());
        return iCard;
    }

    public CardDTO mapICardToCardDTO(ICard iCard){
        CardDTO cardDTO =  new CardDTO();
        cardDTO.setCardNumber(iCard.getCardNumber());
        cardDTO.setExpMonth(iCard.getExpMonth());
        cardDTO.setExpYear(iCard.getExpYear());
        return cardDTO;
    }
}
