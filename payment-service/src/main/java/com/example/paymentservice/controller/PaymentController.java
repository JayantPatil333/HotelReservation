package com.example.paymentservice.controller;

import com.example.paymentservice.model.ICard;
import com.example.paymentservice.service.IPaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Inject
    private IPaymentService paymentService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String doPayment(ICard card, double amount){
        return paymentService.doPayment(card, amount);
    }

}
