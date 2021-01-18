package com.example.paymentservice.service;

import com.example.paymentservice.model.ICard;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public interface IPaymentService {

    public String doPayment(ICard card, double amount);

    String revertPayment(ICard card, double amount);
}
