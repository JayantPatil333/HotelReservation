package com.reservation.service.implementation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.reservation.entity.ReservationEntity;
import com.reservation.exception.ReservationEntityNotFoundException;
import com.reservation.mapper.IMapper;
import com.reservation.model.ICard;
import com.reservation.model.IGuest;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.ReservationStatus;
import com.reservation.proxy.IGuestInformationProxy;
import com.reservation.proxy.IHotelInformationProxy;
import com.reservation.proxy.IPaymentServiceProxy;
import com.reservation.proxy.model.hotel.IHotel;

import com.reservation.repository.implementation.ReservationRepository;
import com.reservation.service.IReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    public com.reservation.proxy.model.guest.IGuest getGuestById(Long guestId){
        ResponseEntity<com.reservation.proxy.model.guest.IGuest> guestResponseEntity = guestProxy.getGuest(guestId);
        return guestResponseEntity.getBody();
    }

    public IHotel getHotelById(Long hotelId){
        return hotelProxy.getHotelById(hotelId);
    }

    public IReservation requestForReservation(IReservation reservation) throws Exception {
        LOGGER.debug("ReservationService :: requestForReservation :: Request for new reservation {}", reservation);
        ReservationEntity newReservation = reservationRepository.save(mapper.mapIReservationToReservationDTO(reservation));
        hotelProxy.reservationRequest(mapper.mapReservationDTOToIReservation(newReservation), newReservation.getHotelId());
        return mapper.mapReservationDTOToIReservation(newReservation);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public IReservation updateReservation(IReservation reservation) throws ReservationEntityNotFoundException {
        LOGGER.debug("ReservationService :: updateReservation :: Request to update reservation {} ", reservation);
        ReservationEntity reservationEntity = reservationRepository.getReservationById(reservation.getReservationId());
        if (reservationEntity != null) {
            reservationEntity.setState(reservation.getState().toString());
            hotelProxy.updateReservation(reservation.getHotelId(), reservation);
            if (reservation.getState().equals(ReservationStatus.CONFIRM)) {
                guestProxy.addStayByGuest(reservation.getGuestId(), reservation.getReservationId());
                reservationEntity.setCard(mapper.mapICardToCardDTO(reservation.getCard()));
                guestProxy.addNewCard(reservationEntity.getGuestId(), reservation.getCard());
                doPayment(reservation.getCard(), reservation.getAmount(), reservation.getReservationId());
            }
            else if(reservation.getState().equals(ReservationStatus.CANCELLED)){
                paymentServiceProxy.revertPayment(mapper.mapICardToProxy(mapper.cardDTOToICard(reservationEntity.getCard())), reservationEntity.getAmount());
            }

            return mapper.mapReservationDTOToIReservation(reservationEntity);
        }
        else
        {
            throw new ReservationEntityNotFoundException("Reservation Entity with ID "+reservation.getReservationId()+" not found.");
        }
    }

    @HystrixCommand(fallbackMethod = "doPaymentFallBack")
    public String doPayment(ICard card, double amount, Long reservationId){
        return paymentServiceProxy.doPayment(mapper.mapICardToProxy(card), amount);
    }

    @SuppressWarnings("unused")
    public String doPaymentFallBack(ICard card, double amount, Long reservationId){
        LOGGER.error("Payment Service is down while handling payment over card details: {}", card);

        return "SUCCESS";
    }

    public IReservation getReservation(Long id, boolean isDetailsRequired) throws Exception {
         ReservationEntity reservationEntity = reservationRepository.getReservationById(id);
         IReservation reservation = mapper.mapReservationDTOToIReservation(reservationEntity);
         if (isDetailsRequired){
             IHotel hotel = getHotelById(reservationEntity.getHotelId());
             com.reservation.proxy.model.guest.IGuest guest = getGuestById(reservationEntity.getGuestId());
             reservation.setHotel(hotel);
             reservation.setGuest(guest);
         }
         return reservation;
    }

    @Override
    public com.reservation.model.IGuest getReservationsByGuestId(Long guestId) {
        com.reservation.proxy.model.guest.IGuest guest = getGuestById(guestId);
        List<IReservation> reservationList = guest.getReservations().stream().map(reservationRepository :: getReservationById)
                .map(mapper::mapReservationDTOToIReservation)
                .collect(Collectors.toList());
        IGuest resultingGuest = mapper.mapProxyGuestToIGuest(guest);
        resultingGuest.setReservations(reservationList);
        return resultingGuest;
    }

}
