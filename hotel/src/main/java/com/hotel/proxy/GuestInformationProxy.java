package com.hotel.proxy;

import com.hotel.proxy.model.IGuest;
import com.hotel.proxy.model.implementation.Guest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "ZuulApiGateway")
public interface GuestInformationProxy {

    @RequestMapping(value = "/GuestService/guest/{guestId}", method = RequestMethod.GET)
    public IGuest getGuest(@PathVariable("guestId") Long guestId);

}
