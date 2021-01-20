package com.reservation.proxy;

import com.reservation.proxy.model.payment.ICard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ZuulApiGateway")
public interface IPaymentServiceProxy {

    @RequestMapping(value = "/PaymentService/payments",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String doPayment(@RequestBody ICard card,@RequestParam("amount") double amount);

    @RequestMapping(value = "/PaymentService/payments/revert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String revertPayment(@RequestBody ICard card,@RequestParam("amount") double amount);

}
