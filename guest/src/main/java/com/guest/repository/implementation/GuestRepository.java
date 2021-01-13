package com.guest.repository.implementation;

import com.guest.dto.GuestDTO;
import com.guest.repository.IGuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class GuestRepository {

    @Autowired
    private IGuestRepository repository;
    public GuestDTO save(GuestDTO guestDTO){
        return repository.save(guestDTO);
    }

    public GuestDTO findById(Long id) throws EntityNotFoundException {
        Optional<GuestDTO> guestOption = repository.findById(id);
        return guestOption.isPresent() ? guestOption.get() : guestOption.orElseThrow(()-> new EntityNotFoundException("Guest not found."));
    }
}
