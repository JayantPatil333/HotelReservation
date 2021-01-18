package com.reservation.service.implementation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.reservation.dto.ReservationDTO;
import com.reservation.mapper.IMapper;
import com.reservation.model.ICard;
import com.reservation.model.IReservation;
import com.reservation.proxy.IGuestInformationProxy;
import com.reservation.proxy.IHotelInformationProxy;
import com.reservation.proxy.IPaymentServiceProxy;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.proxy.model.hotel.IHotel;

import com.reservation.repository.implementation.ReservationRepository;
import com.reservation.service.IReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class ReservationService implements IReservationService {

    @Autowired
    private IGuestInformationProxy guestProxy;

    @Autowired
    private IHotelInformationProxy hotelProxy;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private IPaymentServiceProxy paymentServiceProxy;

    @Inject
    private IMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

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

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation confirmReservation(IReservation reservation){
        hotelProxy.confirmReservation(reservation.getReservationId());
        guestProxy.addStayByGuest(reservation.getGuestId(), reservation.getReservationId());
        ReservationDTO reservationDTO = reservationRepository.getReservationById(reservation.getReservationId());
        reservationDTO.setState("CONFIRMED");
        return mapper.mapReservationDTOToIReservation(reservationDTO);
    }

    @HystrixCommand(fallbackMethod = "doPaymentFallBack")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String doPayment(ICard card, double amount, Long reservationId){
        ReservationDTO reservation = reservationRepository.getReservationById(reservationId);
        reservation.setCard(mapper.mapICardToCardDTO(card));
        return paymentServiceProxy.doPayment(mapper.mapICardToProxy(card), amount);
    }

    @SuppressWarnings("unused")
    public String doPaymentFallBack(ICard card, double amount, Long reservationId){
        LOGGER.error("Payment Service is down while handling payment over card details: {}", card);

        return "SUCCESS";
    }

    public IReservation getReservation(Long id, boolean isDetailsRequired){
         ReservationDTO reservationDTO = reservationRepository.getReservationById(id);
         IReservation reservation = mapper.mapReservationDTOToIReservation(reservationDTO);
         if (isDetailsRequired){
             IHotel hotel = getHotelById(reservationDTO.getHotelId());
             IGuest guest = getGuestById(reservationDTO.getGuestId());
             reservation.setHotel(hotel);
             reservation.setGuest(guest);
         }
         return reservation;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation cancelReservation(Long id, double amount) throws Exception {
        ReservationDTO reservationDTO = reservationRepository.getReservationById(id);
        ResponseEntity<IReservation> cancelReservation = hotelProxy.cancelReservation(reservationDTO.getHotelId(), reservationDTO.getReservationId());
        if(cancelReservation.getStatusCode().is2xxSuccessful()){
            paymentServiceProxy.revertPayment( mapper.mapICardToProxy(mapper.cardDTOToICard(reservationDTO.getCard())), amount);
            reservationDTO.setState("CANCELLED");
        }
        else {
            throw new Exception("Request get rejected by Hotel.");
        }
        return mapper.mapReservationDTOToIReservation(reservationDTO);
    }
}
