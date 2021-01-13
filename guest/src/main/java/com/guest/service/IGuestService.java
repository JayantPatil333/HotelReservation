package com.guest.service;

import com.guest.dto.GuestDTO;
import com.guest.model.IGuest;
import com.guest.model.IStay;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public interface IGuestService {

    public IGuest addNewGuest(IGuest guest);

    public IGuest getGuest(Long id) throws EntityNotFoundException;

    public String addStayByGuest(Long guestId, Long stay) throws EntityNotFoundException;

    public List<IGuest> getGuests(List<Long> guestIds ) throws EntityNotFoundException;
}
