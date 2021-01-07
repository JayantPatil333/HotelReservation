package com.reservation.proxy;

import com.reservation.proxy.model.guest.IGuest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ZuulApiGateway")
public interface IGuestInformationProxy {
    @RequestMapping(value = "/GuestService/guest/{guestId}", method = RequestMethod.GET)
    public IGuest getGuest(@PathVariable("guestId") Long guestId);

    @RequestMapping(value = "/GuestService/guest/addNewStay", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStayByGuest(@RequestParam("guestId") Long guestId, @RequestParam("reservationId") Long stay);
}
