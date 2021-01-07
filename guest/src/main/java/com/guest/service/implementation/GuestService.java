package com.guest.service.implementation;

import com.guest.dto.GuestDTO;
import com.guest.dto.StayDTO;
import com.guest.mapper.IMapper;
import com.guest.model.IGuest;
import com.guest.model.IStay;
import com.guest.proxy.IHotelInformationProxy;
import com.guest.proxy.model.IHotel;
import com.guest.repository.GuestRepository;
import com.guest.service.IGuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GuestService implements IGuestService {

    final private Logger LOGGER = LoggerFactory.getLogger(GuestService.class);

    @Autowired
    private GuestRepository repository;

    @Autowired
    private IHotelInformationProxy hotelProxy;

    @Inject
    private IMapper mapper;

    public IGuest addNewGuest(IGuest guest) {
        LOGGER.debug("Request to add new guest.{}"+guest);
        GuestDTO savedGuest = repository.save(mapper.mapIGuestToGuestDTO(guest));
        return mapper.mapGuestDTOToIGuest(savedGuest);
    }

    public IGuest getGuest(Long id, boolean isToFetchHotelInfo) {
        Optional<GuestDTO> guest = repository.findById(id);
        if(guest.isPresent()){
           IGuest iGuest = mapper.mapGuestDTOToIGuest(guest.get());
           /*if(isToFetchHotelInfo) {
               List<Long> hotelIds = iGuest.getStayList().stream().map(IStay::getHotelId).collect(Collectors.toList());

               List<IHotel> hotels = hotelProxy.getHotels(hotelIds);

               for (IStay iStay : iGuest.getStayList()) {
                   IHotel matchedHotel = hotels.stream().filter(iHotel -> iHotel.getHotelId() == iStay.getHotelId()).findFirst().get();
                   iStay.setHotel(mapper.mapProxyHotelToIHotel(matchedHotel));
               }
           }*/
           return iGuest;
        }

        return null;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String addStayByGuest(Long guestId, Long reservationId){
        Optional<GuestDTO> guest = repository.findById(guestId);
        if(guest.isPresent()){
            guest.get().getReservations().add(reservationId);
            return "Stay information stored.";
        }
        return "Guest information is not found.";
    }

    public List<IGuest> getGuests(List<Long> guestIds ){
        List<IGuest> guests = new ArrayList();
        for(Long guestId : guestIds){
            IGuest guest = getGuest(guestId, true);
            guests.add(guest);
        }
        return guests;
    }

}
