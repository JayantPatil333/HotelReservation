package com.guest.proxy;


import com.guest.proxy.model.IHotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ZuulApiGateway")
public interface IHotelInformationProxy {
    @RequestMapping(value = "/HotelService/hotel/getHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IHotel> getHotels(@RequestParam List<Long> hotelIds);

}
