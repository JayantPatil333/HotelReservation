package com.hotel.services.implementation;


import com.hotel.dto.AddressDTO;
import com.hotel.dto.HotelDTO;
import com.hotel.dto.ReservationDTO;
import com.hotel.dto.RoomDTO;
import com.hotel.model.IAddress;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.*;
import com.hotel.repository.implementation.HotelRepositoryImpl;
import com.hotel.repository.implementation.ReservationRepositoryImpl;
import com.hotel.services.IHotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private IHotelService hotelService;

    @MockBean
    private HotelRepositoryImpl hotelRepository;

    @MockBean
    private ReservationRepositoryImpl reservationRepository;

    private IAddress address = new Address(1L, "Lane no1", "Hanuman Nagar", "Pune","412308");
    private IHotel hotel = new Hotel(1L, "City Inn", "1234567890",3, address);
    private IReservation reservation = new Reservation(new Room(),1L, new Date(), new Date(), 1L, "REQUEST", "SINGLE");

    private AddressDTO addressDTO =  new AddressDTO(1L, "Lane no1", "Hanuman Nagar", "Pune","412308");
    private HotelDTO hotelDTO =  new HotelDTO(1L, "City Inn", "1234567890",3, addressDTO);
    private RoomDTO roomDTO =  new RoomDTO(102, 1000, RoomType.SINGLE);
    private ReservationDTO reservationDTO =  new ReservationDTO(new RoomDTO(),1L, new Date(), new Date(), 1L, "REQUEST", "SINGLE");
    private ReservationDTO reservationDTO2 =  new ReservationDTO(new RoomDTO(),2L, new Date(), new Date(), 1L, "REQUEST", "SINGLE");

    @Test
    public void addNewHotel() {
        given(hotelRepository.save(any())).willReturn(hotelDTO);
        IHotel iHotel = hotelService.addNewHotel(hotel);
        assertEquals(iHotel.getHotelId(), hotelDTO.getHotelId());
    }

    @Test
    public void confirmReservation() {
        given(reservationRepository.findReservationById(anyLong())).willReturn(reservationDTO);
        IReservation iReservation = hotelService.confirmReservation(1L);
        assertTrue(iReservation.getState().equals("DONE"));
    }

    @Test
    public void getReservationByGuestIdPerHotel() {

        hotelDTO.getReservations().add(reservationDTO);
        hotelDTO.getReservations().add(reservationDTO2);
        given(hotelRepository.findById(anyLong())).willReturn(hotelDTO);
        List<IReservation> reservation = hotelService.getReservationByGuestIdPerHotel(1L, 1L);

        assertFalse(reservation.stream().filter(r -> !r.getGuestId().equals(1L)).findFirst().isPresent());

    }

    @Test
    public void cancelReservation() {
        given(reservationRepository.findReservationById(anyLong())).willReturn(reservationDTO);
        IReservation iReservation = hotelService.cancelReservation(1L, 1L);
        assertTrue(iReservation.getReservationId().equals(1L));
        assertTrue(iReservation.getState().equals("CANCELLED"));
    }

    @Test(expected = EntityNotFoundException.class)
    public void cancelReservation_EntityNotFound() {
        given(reservationRepository.findReservationById(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        IReservation iReservation = hotelService.cancelReservation(1L, 1L);
        assertTrue(iReservation.getReservationId().equals(1L));
        assertTrue(iReservation.getState().equals("CANCELLED"));
    }

    @Test
    public void reservationRequest() {
        hotelDTO.setReservations(new ArrayList<>());
        hotelDTO.getRooms().add(roomDTO);
        given(hotelRepository.findById(anyLong())).willReturn(hotelDTO);
        IReservation iReservation = hotelService.reservationRequest(1L, reservation);
        assertTrue(iReservation.getState().equals("REQUEST"));
    }

    @Test
    public void getAllReservationsByHotelId() {
        given((hotelRepository.findById(anyLong()))).willReturn(hotelDTO);
        hotelDTO.getReservations().add(reservationDTO2);
        hotelDTO.getReservations().add(reservationDTO);
        List<IReservation> reservations = hotelService.getAllReservationsByHotelId(1L);

        assertEquals(reservations.size(), 2);
    }

    @Test
    public void getHotels() {
    }

    @Test
    public void getHotelById() {
        given(hotelRepository.findById(anyLong())).willReturn(hotelDTO);
        IHotel iHotel = hotelService.getHotelById(1L);
        assertTrue(iHotel.getHotelId().equals(1L));
    }

    @Test(expected = EntityNotFoundException.class)
    public void getHotelById_EntityNotFound() {
        given(hotelRepository.findById(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        hotelService.getHotelById(1L);
    }

    @Test
    public void searchHotels() {
        AddressDTO addressDTO1 =  new AddressDTO(2L, "Lane no1", "Hanuman Nagar", "Kolhapur","412308");
        HotelDTO hotelDTO1 =  new HotelDTO(2L, "City Inn", "1234567890",3, addressDTO1);
        given(hotelRepository.getAllHotels()).willReturn(Arrays.asList(hotelDTO, hotelDTO1));
        List<IHotel> hotels = hotelService.searchHotels("Pune", new Date(), new Date(), "SINGLE");

        assertEquals(hotels.size(), 1);
        assertFalse(hotels.stream().filter(h -> !h.getAddress().getCity().equals("Pune")).findFirst().isPresent());

    }
}