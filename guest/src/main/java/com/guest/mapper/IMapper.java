package com.guest.mapper;

import com.guest.dto.GuestDTO;
import com.guest.dto.StayDTO;
import com.guest.model.IGuest;
import com.guest.model.IHotel;
import com.guest.model.IStay;

public interface IMapper {
    public IGuest mapGuestDTOToIGuest(GuestDTO guestDTO);

    public GuestDTO mapIGuestToGuestDTO(IGuest iGuest);

    public StayDTO mapIStayToStayDTO(IStay iStay);

    public IHotel mapProxyHotelToIHotel(com.guest.proxy.model.IHotel proxyHotel);
}
