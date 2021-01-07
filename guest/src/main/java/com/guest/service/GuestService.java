package com.guest.service;
import com.guest.dto.GuestDTO;
import com.guest.mapper.IMapper;
import com.guest.model.IGuest;
import com.guest.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.Optional;

public class GuestService {

    @Autowired
    private GuestRepository repository;

    @Inject
    private IMapper mapper;

    public IGuest addNewGuest(IGuest guest){
        GuestDTO savedGuest = repository.save(mapper.mapIGuestToGuestDTO(guest));
        return mapper.mapGuestDTOToIGuest(savedGuest);
    }

    public IGuest getGuest(Long id){
        Optional<GuestDTO> guest =  repository.findById(id);
        return guest.isPresent() ? mapper.mapGuestDTOToIGuest(guest.get()):null;
    }

}
