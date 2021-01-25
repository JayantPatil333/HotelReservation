package com.reservation.proxy;

import com.reservation.model.ICard;
import com.reservation.proxy.model.guest.IGuest;
import com.reservation.response.ApiResponseImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ZuulApiGateway")
public interface IGuestInformationProxy {
    @GetMapping(value = "/GuestService/guests/{guestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<IGuest> getGuest(@PathVariable("guestId") Long guestId);

    @PutMapping(value = "/GuestService/guests/{guestId}/stay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<IGuest> addStayByGuest(@PathVariable("guestId") Long guestId, @RequestParam("reservationId") Long stay);

    @PostMapping(value = "/GuestService/guests/{guestId}/card", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseImpl<IGuest> addNewCard(@PathVariable("guestId") Long guestId,@RequestBody ICard card);
}
