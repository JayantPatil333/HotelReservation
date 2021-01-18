package com.example.paymentservice.controller;

import com.example.paymentservice.model.ICard;
import com.example.paymentservice.service.IPaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Inject
    private IPaymentService paymentService;

    @PostMapping
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String doPayment(@RequestBody ICard card,@RequestParam("amount") double amount){
        return paymentService.doPayment(card, amount);
    }

    @PostMapping
    @RequestMapping(value = "/revert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String revertPayment(@RequestBody ICard card,@RequestParam("amount") double amount){
        return paymentService.revertPayment(card, amount);
    }

}
