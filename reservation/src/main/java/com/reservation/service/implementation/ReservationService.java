package com.reservation.service.implementation;

import com.reservation.dto.ReservationDTO;
import com.reservation.mapper.IMapper;
import com.reservation.model.IReservation;
import com.reservation.proxy.IGuestInformationProxy;
import com.reservation.proxy.IHotelInformationProxy;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;
import com.reservation.repository.IReservationRepository;
import com.reservation.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class ReservationService implements IReservationService {

    @Autowired
    private IGuestInformationProxy guestProxy;

    @Autowired
    private IHotelInformationProxy hotelProxy;

    @Autowired
    private IReservationRepository reservationRepository;

    @Inject
    private IMapper mapper;

    public IGuest getGuestById(Long guestId){
        return  guestProxy.getGuest(guestId);
    }

    public IHotel getHotelById(Long hotelId){
        return hotelProxy.getHotelById(hotelId);
    }

    public String requestForReservation(IReservation reservation){
        ReservationDTO newReservation = reservationRepository.save(mapper.mapIReservationToReservationDTO(reservation));
        return hotelProxy.reservationRequest(mapper.mapReservationDTOToIReservation(newReservation), reservation.getHotelId());
    }

    public String confirmReservation(IReservation reservation){
        hotelProxy.confirmReservation(reservation.getReservationId());
        guestProxy.addStayByGuest(reservation.getGuestId(), reservation.getReservationId());
        return "SUCCESS";
    }

    /*public List<IHotel> searchForHotels(String city, Date fromDate, Date toDate, String roomType){
        return hotelProxy.searchHotels(city, fromDate, toDate, roomType);
    }*/

}
