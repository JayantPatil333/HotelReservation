package com.reservation.message.service;

import com.reservation.message.PaymentStream;
import com.reservation.message.model.PaymentPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    private PaymentStream paymentStream;

    public PaymentService(PaymentStream paymentStream){
        this.paymentStream = paymentStream;
    }

    public void sendPaymentRequest(PaymentPayload paymentPayload){
        log.debug("Sending payment request {} ", paymentPayload);
        MessageChannel messageChannel = this.paymentStream.sendPaymentSteam();
        messageChannel.send(MessageBuilder.withPayload(paymentPayload)
                            .setHeader(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .build());
    }

}
