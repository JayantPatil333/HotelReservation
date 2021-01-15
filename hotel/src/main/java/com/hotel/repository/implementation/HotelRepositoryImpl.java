package com.hotel.repository.implementation;

import com.hotel.dto.HotelDTO;
import com.hotel.model.IHotel;
import com.hotel.repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class HotelRepositoryImpl {

    @Autowired
    private IHotelRepository hotelRepository;

    public HotelDTO findById(Long id) throws EntityNotFoundException {
        Optional<HotelDTO> hotelDTO = hotelRepository.findById(id);
        return hotelDTO.isPresent() ? hotelDTO.get() :hotelDTO.orElseThrow(() ->new EntityNotFoundException("Hotel information not found :"+id));
    }

    public HotelDTO save(HotelDTO hotel){
        return hotelRepository.save(hotel);
    }

    public List<HotelDTO> getAllHotels(){
        return hotelRepository.findAll();
    }
}
