package com.reservation.service.implementation;

import com.reservation.dto.ReservationDTO;
import com.reservation.mapper.IMapper;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.Reservation;
import com.reservation.proxy.IGuestInformationProxy;
import com.reservation.proxy.IHotelInformationProxy;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;
import com.reservation.repository.IReservationRepository;
import com.reservation.service.IReservationService;
import org.aspectj.asm.IRelationship;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

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
}
