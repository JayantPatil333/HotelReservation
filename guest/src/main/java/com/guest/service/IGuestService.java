package com.guest.service;

import com.guest.dto.GuestDTO;
import com.guest.model.IGuest;
import com.guest.model.IStay;

import java.util.List;
import java.util.Optional;

public interface IGuestService {

    public IGuest addNewGuest(IGuest guest);

    public IGuest getGuest(Long id);

    public String addStayByGuest(Long guestId, Long stay);

    public List<IGuest> getGuests(List<Long> guestIds );
}
