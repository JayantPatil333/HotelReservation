package com.hotel.proxy;

import com.hotel.proxy.model.Guest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "ZuulApiGateway")
//@RibbonClient(name = "GuestService")
public interface GuestInformationProxy {

    @RequestMapping(value = "/GuestService/guest/{guestId}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable("guestId") Long guestId);
}
