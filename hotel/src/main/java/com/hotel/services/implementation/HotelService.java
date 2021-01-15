package com.hotel.services.implementation;

import com.hotel.dto.HotelDTO;
import com.hotel.dto.ReservationDTO;
import com.hotel.mapper.IMapper;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.IRoom;
import com.hotel.model.implementation.Hotel;
import com.hotel.model.implementation.Reservation;
import com.hotel.repository.implementation.HotelRepositoryImpl;
import com.hotel.repository.implementation.ReservationRepositoryImpl;
import com.hotel.services.IHotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelService implements IHotelService {

    @Autowired
    private HotelRepositoryImpl hotelRepository;

    @Autowired
    private ReservationRepositoryImpl reservationRepository;

    @Inject
    private IMapper imapper;

    Logger LOG = LoggerFactory.getLogger(HotelService.class);

    public IHotel addNewHotel(IHotel hotel)
    {
        HotelDTO hotelDTO = imapper.mapIHotelToHotelDTO(hotel);
        HotelDTO saved = hotelRepository.save(hotelDTO);
        return imapper.mapHotelDTOToIHotel(saved);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation confirmReservation(Long reservationId)
    {
        ReservationDTO reservationDTO = reservationRepository.findReservationById(reservationId);
        reservationDTO.setState("DONE");
        return imapper.mapReservationDTOToIReservation(reservationDTO);
    }

    public List<IReservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId ){
        HotelDTO hotelDTO = hotelRepository.findById(hotelId);
        List<IReservation> reservations = hotelDTO.getReservations().stream().filter(reservationDTO -> reservationDTO.getGuestId() == guestId)
                    .map(imapper::mapReservationDTOToIReservation).collect(Collectors.toList());
        return reservations;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation cancelReservation(Long hotelId, Long reservationId){
        ReservationDTO reservation = reservationRepository.findReservationById(reservationId);
        reservation.setState("CANCELLED");

        return imapper.mapReservationDTOToIReservation(reservation);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation reservationRequest(Long hotelId, IReservation reservation){
        HotelDTO hotel = hotelRepository.findById(hotelId);
        reservation.setRoom(findAvailableRoom(imapper.mapHotelDTOToIHotel(hotel), reservation.getFromDate(), reservation.getToDate()));
        hotel.getReservations().add(imapper.mapIReservationToReservationDTO(reservation));
        return reservation;
    }

    /*private boolean isRoomAvailableForDates(HotelDTO hotel, Date fromDate, Date toDate){
        Date dateToCheck = fromDate;
        while (dateToCheck.after(toDate) || dateToCheck.equals(toDate)){
            List<Long> reservedRooms = hotel.getReservationsByDate().get(dateToCheck);
            if(reservedRooms.size() < hotel.getRooms().size())
                dateToCheck.setDate(dateToCheck.getDate() + 1);
            else
                return false;
        }
        return true;
    }*/

    private IRoom findAvailableRoom(IHotel hotel, Date fromDate, Date toDate){
        List<IRoom> reservedRooms = hotel.getReservations().stream().filter(iReservation -> iReservation.getFromDate().equals(fromDate))
                .map(iReservation -> iReservation.getRoom()).collect(Collectors.toList());

        Optional<IRoom> availableRoom = hotel.getRooms().stream().filter(room -> ! reservedRooms.contains(room)).findFirst();

        return availableRoom.isPresent() ? availableRoom.get():null;
    }

    public List<IReservation> getAllReservationsByHotelId(Long hotelId){
        HotelDTO hotel = hotelRepository.findById(hotelId);
        return hotel.getReservations().stream().map(imapper:: mapReservationDTOToIReservation).collect(Collectors.toList());
    }

    public List<IHotel> getHotels(List<Long> hotelIds){
        List<IHotel> hotels =  new ArrayList();
        for(Long hotelId : hotelIds){
            HotelDTO hotel = hotelRepository.findById(hotelId);
            hotels.add(imapper.mapHotelDTOToIHotel(hotel));
        }
        return hotels;
    }

    public IHotel getHotelById(Long hotelId){
        IHotel hotel = new Hotel();
        HotelDTO hotelDTO =  hotelRepository.findById(hotelId);
        hotel = imapper.mapHotelDTOToIHotel(hotelDTO);
        return hotel;
    }

    public List<IHotel> searchHotels(String city, Date fromDate, Date toDate, String roomType ){
        List<HotelDTO> hotels = hotelRepository.getAllHotels();
        List<IHotel> outputHotels =  hotels.stream().filter(h -> h.getAddress().getCity().equals(city))
                                    .map(imapper :: mapHotelDTOToIHotel).collect(Collectors.toList());
        return outputHotels;
    }

}
