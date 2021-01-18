package com.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reservation.model.IReservation;
import com.reservation.model.implementation.Reservation;
import com.reservation.proxy.model.payment.ICard;
import com.reservation.proxy.model.payment.implementation.Card;
import com.reservation.service.IReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private IReservationService reservationService;

    private ICard card =  new Card("1234567890","12","2024");
    private IReservation reservation = new Reservation(new Date(), new Date(), 1L, 1L, 1L, "REQUEST","SINGLE", new com.reservation.model.implementation.Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear()));
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getGuestById() {
    }

    @Test
    public void getHotelById() {
    }

    @Test
    public void reservationRequest() throws Exception {
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(reservation);
        given(reservationService.requestForReservation(any())).willReturn("Request submitted.");
        mockMvc.perform(post("/reservation/request")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .with(user("Guest")
                            .password("password")
                            .roles("GUEST")))
                .andExpect(status().isOk());
    }

    @Test
    public void confirmReservation() throws Exception {
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(reservation);
        given(reservationService.confirmReservation(any())).willReturn(reservation);
        mockMvc.perform(patch("/reservation/confirm")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());

    }

    @Test
    public void doPayment() throws Exception {
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(card);
        given(reservationService.doPayment(any(), anyDouble(), anyLong())).willReturn("SUCCESS");
        mockMvc.perform(post("/reservation/payment")
                .content(json)
                .param("amount","500.0")
                .param("reservationId","1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());

    }

    @Test
    public void getReservation() throws Exception {
        given(reservationService.getReservation(anyLong(), anyBoolean())).willReturn(reservation);
        mockMvc.perform(get("/reservation")
                .param("id","1")
                .param("isDetailsRequired","false")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());
    }

    @Test
    public void cancelReservation() throws Exception {
        reservation.setState("CANCELLED");
        given(reservationService.cancelReservation(anyLong(), anyDouble())).willReturn(reservation);
        mockMvc.perform(patch("/reservation/cancel")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("reservationId","1")
                        .param("amount","500")
                        .with(user("Guest")
                                .password("password")
                                .roles("GUEST"))
                        )
                .andExpect(status().isOk());

    }
}