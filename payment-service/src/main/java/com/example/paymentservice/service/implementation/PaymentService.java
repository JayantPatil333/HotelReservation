package com.example.paymentservice.service.implementation;

import com.example.paymentservice.model.ICard;
import com.example.paymentservice.service.IPaymentService;

public class PaymentService implements IPaymentService {

    public String doPayment(ICard card, double amount){
        return "SUCCESS";
    }

}
