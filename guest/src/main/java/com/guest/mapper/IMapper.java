package com.guest.mapper;

import com.guest.dto.GuestDTO;
import com.guest.model.IGuest;

public interface IMapper {
    public IGuest mapGuestDTOToIGuest(GuestDTO guestDTO);

    public GuestDTO mapIGuestToGuestDTO(IGuest iGuest);
}
