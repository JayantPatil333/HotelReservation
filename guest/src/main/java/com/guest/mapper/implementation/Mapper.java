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
        return iGuest;
    }

    public IHistory mapHistoryDTOToIHistory(HistoryDTO historyDTO){
        IHistory iHistory = new History();
        iHistory.setStayList(historyDTO.getStayList().stream().map(this::mapStayDTOToIStay).collect(Collectors.toList()));
        iHistory.setCancelledStay(historyDTO.getCancelledStay().stream().map(this::mapStayDTOToIStay).collect(Collectors.toList()));
        return iHistory;
    }

    public IStay mapStayDTOToIStay(StayDTO stayDTO)
    {
        IStay iStay = new Stay();
        iStay.setCancelled(stayDTO.isCancelled());
        iStay.setFromDate(stayDTO.getFromDate());
        iStay.setToDate(stayDTO.getToDate());
        iStay.setHotel(mapHotelDTOToIHotel(stayDTO.getHotel()));
        iStay.setPaidBy(stayDTO.getPaidBy());
        iStay.setReasonToCancel(stayDTO.getReasonToCancel());

        return iStay;
    }

    public IHotel mapHotelDTOToIHotel(HotelDTO hotelDTO){
        IHotel iHotel = new Hotel();
        iHotel.setName(hotelDTO.getName());
        iHotel.setContactNumber(hotelDTO.getContactNumber());
        iHotel.setAddress(mapAddressDTOToIAddress(hotelDTO.getAddress()));
        return iHotel;
    }

    public IAddress mapAddressDTOToIAddress(AddressDTO addressDTO){
        IAddress iAddress =  new Address();
        iAddress.setCity(addressDTO.getCity());
        iAddress.setLocation(addressDTO.getLocation());
        iAddress.setStreet(addressDTO.getStreet());
        return iAddress;
    }
    @Override
    public GuestDTO mapIGuestToGuestDTO(IGuest iGuest) {
        GuestDTO guestDTO =  new GuestDTO();
        guestDTO.setGuestId(iGuest.getGuestId());
        guestDTO.setEmail(iGuest.getEmail());
        guestDTO.setRatting(iGuest.getRatting());
        guestDTO.setContactNumber(iGuest.getContactNumber());
        guestDTO.setHistory(mapIHistoryToHistoryDTO(iGuest.getHistory()));
        return guestDTO;
    }

    public HistoryDTO mapIHistoryToHistoryDTO(IHistory iHistory){
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setStayList(iHistory.getStayList().stream().map(this::mapIStayToStayDTO).collect(Collectors.toList()));
        historyDTO.setCancelledStay(iHistory.getCancelledStay().stream().map(this::mapIStayToStayDTO).collect(Collectors.toList()));
        return historyDTO;
    }

    public StayDTO mapIStayToStayDTO(IStay iStay){
        StayDTO stayDTO =  new StayDTO();
        stayDTO.setCancelled(iStay.isCancelled());
        stayDTO.setFromDate(iStay.getFromDate());
        stayDTO.setToDate(iStay.getToDate());
        stayDTO.setPaidBy(iStay.getPaidBy());
        stayDTO.setReasonToCancel(iStay.getReasonToCancel());
        stayDTO.setHotel(mapIHotelToHotelDTO(iStay.getHotel()));
        return stayDTO;
    }

    public HotelDTO mapIHotelToHotelDTO(IHotel iHotel){
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName(iHotel.getName());
        hotelDTO.setContactNumber(iHotel.getContactNumber());
        hotelDTO.setAddress(mapIAddressToAddressDTO(iHotel.getAddress()));
        return hotelDTO;
    }

    public AddressDTO mapIAddressToAddressDTO(IAddress iAddress){
        AddressDTO addressDTO =  new AddressDTO();
        addressDTO.setCity(iAddress.getCity());
        addressDTO.setLocation(iAddress.getLocation());
        addressDTO.setStreet(iAddress.getStreet());
        return addressDTO;
    }
}
