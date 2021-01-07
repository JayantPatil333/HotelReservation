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
        //iGuest.setStayList(guestDTO.getStayList().stream().map(this::mapStayDTOToIStay).collect(Collectors.toList()));
        return iGuest;
    }

    public IStay mapStayDTOToIStay(StayDTO stayDTO)
    {
        IStay iStay = new Stay();
        iStay.setStayId(stayDTO.getStayId());
        iStay.setCancelled(stayDTO.isCancelled());
        iStay.setFromDate(stayDTO.getFromDate());
        iStay.setToDate(stayDTO.getToDate());
        //iStay.setHotel(mapHotelDTOToIHotel(stayDTO.getHotel()));
        iStay.setPaidBy(stayDTO.getPaidBy());
        iStay.setReasonToCancel(stayDTO.getReasonToCancel());
        iStay.setHotelId(stayDTO.getHotelId());
        return iStay;
    }

    /*public IHotel mapHotelDTOToIHotel(HotelDTO hotelDTO){
        IHotel iHotel = new Hotel();
        iHotel.setHotelId(hotelDTO.getHotelId());
        iHotel.setName(hotelDTO.getName());
        iHotel.setContactNumber(hotelDTO.getContactNumber());
        iHotel.setAddress(mapAddressDTOToIAddress(hotelDTO.getAddress()));
        return iHotel;
    }*/

    /*public IAddress mapAddressDTOToIAddress(AddressDTO addressDTO){
        IAddress iAddress =  new Address();
        iAddress.setAddressId(addressDTO.getAddressId());
        iAddress.setCity(addressDTO.getCity());
        iAddress.setLocation(addressDTO.getLocation());
        iAddress.setStreet(addressDTO.getStreet());
        return iAddress;
    }*/
    @Override
    public GuestDTO mapIGuestToGuestDTO(IGuest iGuest) {
        GuestDTO guestDTO =  new GuestDTO();
        guestDTO.setGuestId(iGuest.getGuestId());
        guestDTO.setName(iGuest.getName());
        guestDTO.setEmail(iGuest.getEmail());
        guestDTO.setRatting(iGuest.getRatting());
        guestDTO.setContactNumber(iGuest.getContactNumber());
        guestDTO.setCards(iGuest.getCards().stream().map(this::mapICardToCardDTO).collect(Collectors.toList()));
        //guestDTO.setStayList(iGuest.getStayList().stream().map(this::mapIStayToStayDTO).collect(Collectors.toList()));
        return guestDTO;
    }

    public StayDTO mapIStayToStayDTO(IStay iStay){
        StayDTO stayDTO =  new StayDTO();
        stayDTO.setHotelId(iStay.getHotelId());
        stayDTO.setStayId(iStay.getStayId());
        stayDTO.setCancelled(iStay.isCancelled());
        stayDTO.setFromDate(iStay.getFromDate());
        stayDTO.setToDate(iStay.getToDate());
        stayDTO.setPaidBy(iStay.getPaidBy());
        stayDTO.setReasonToCancel(iStay.getReasonToCancel());
       // stayDTO.setHotel(mapIHotelToHotelDTO(iStay.getHotel()));
        return stayDTO;
    }

    /*public HotelDTO mapIHotelToHotelDTO(IHotel iHotel){
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotelId(iHotel.getHotelId());
        hotelDTO.setName(iHotel.getName());
        hotelDTO.setContactNumber(iHotel.getContactNumber());
        hotelDTO.setAddress(mapIAddressToAddressDTO(iHotel.getAddress()));
        return hotelDTO;
    }

    public AddressDTO mapIAddressToAddressDTO(IAddress iAddress){
        AddressDTO addressDTO =  new AddressDTO();
        addressDTO.setAddressId(iAddress.getAddressId());
        addressDTO.setCity(iAddress.getCity());
        addressDTO.setLocation(iAddress.getLocation());
        addressDTO.setStreet(iAddress.getStreet());
        return addressDTO;
    }*/

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

    public IHotel mapProxyHotelToIHotel(com.guest.proxy.model.IHotel proxyHotel){
        IHotel iHotel = new Hotel();
        iHotel.setHotelId(proxyHotel.getHotelId());
        iHotel.setName(proxyHotel.getName());
        iHotel.setContactNumber(proxyHotel.getPhoneNumber());
        iHotel.setAddress(mapProxyAddressToIAddress(proxyHotel.getAddress()));
        return iHotel;
    }

    public IAddress mapProxyAddressToIAddress(com.guest.proxy.model.IAddress proxyAddress){
        IAddress iAddress =  new Address();
        iAddress.setAddressId(proxyAddress.getAddressId());
        iAddress.setStreet(proxyAddress.getStreet());
        iAddress.setLocation(proxyAddress.getArea());
        iAddress.setCity(proxyAddress.getCity());
        return iAddress;
    }
}
