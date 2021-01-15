package com.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.model.IAddress;
import com.hotel.model.IHotel;
import com.hotel.model.IReservation;
import com.hotel.model.implementation.*;
import com.hotel.services.IHotelService;
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

import java.util.Arrays;
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
public class HotelControllerTest {

    @MockBean
    private IHotelService service;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private IAddress address = new Address(1L, "Lane no1", "Hanuman Nagar", "Pune","412308");
    private IHotel hotel = new Hotel(1L, "City Inn", "1234567890",3, address);
    private IReservation reservation = new Reservation(new Room(),1L, new Date(), new Date(), 1L, "REQUEST", "SINGLE");

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void addNewHotel() throws Exception {
        given(service.addNewHotel(any())).willReturn(hotel);
        ObjectMapper mapper =  new ObjectMapper();
        String json =  mapper.writeValueAsString(hotel);
        mockMvc.perform(post("/hotel")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .with(user("Hotel")
                                .password("password")
                                .roles("HOTEL")))
                        .andExpect(status().isCreated());
    }

    @Test
    public void reservationRequest() throws Exception {
        given(service.reservationRequest(anyLong(), any())).willReturn(reservation);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reservation);
        mockMvc.perform(post("/hotel/reservation")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("hotelId", "1")
                .content(json)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());
    }

    @Test
    public void reservationRequest_ForbiddenForHotelRole() throws Exception {
        given(service.reservationRequest(anyLong(), any())).willReturn(reservation);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reservation);
        mockMvc.perform(post("/hotel/reservation")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("hotelId", "1")
                .content(json)
                .with(user("Hotel")
                        .password("password")
                        .roles("HOTEL")))
                .andExpect(status().isForbidden());
    }

    @Test
    public void cancelReservation() throws Exception {
        given(service.cancelReservation(anyLong(),anyLong())).willReturn(reservation);
        mockMvc.perform(patch("/hotel/cancelReservation")
                        .param("hotelId", "1")
                        .param("reservationId","1")
                        .with(user("Guest")
                            .password("password")
                            .roles("GUEST")))
                        .andExpect(status().isAccepted());

    }

    @Test
    public void getAllReservationsByHotelId() throws Exception {
        given(service.getAllReservationsByHotelId(anyLong())).willReturn(Arrays.asList(reservation));
        mockMvc.perform(get("/hotel/reservationsByHotel")
                .param("hotelId", "1")
                .with(user("Hotel")
                        .password("password")
                        .roles("HOTEL")))
                .andExpect(status().isOk());
    }

    @Test
    public void getReservationByGuest() throws Exception {
        given(service.getReservationByGuestIdPerHotel(anyLong(),anyLong())).willReturn(Arrays.asList(reservation));
        mockMvc.perform(get("/hotel/reservationsByHotel")
                .param("hotelId", "1")
                .param("guestId","1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Hotel")
                        .password("password")
                        .roles("HOTEL")))
                .andExpect(status().isOk());
    }

    @Test
    public void confirmReservation() throws Exception {
        given(service.confirmReservation(anyLong())).willReturn(reservation);
        mockMvc.perform(patch("/hotel/confirmReservation")
                .param("reservationId", "1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isAccepted());

    }

    @Test
    public void getHotels() {

    }

    @Test
    public void getHotelById() throws Exception {
        given(service.getHotelById(anyLong())).willReturn(hotel);
        mockMvc.perform(get("/hotel")
                .param("hotelId", "1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());

    }

    @Test
    public void searchHotels() throws Exception {
        given(service.searchHotels(anyString(), any(), any(), anyString())).willReturn(Arrays.asList(hotel));
        mockMvc.perform(get("/hotel/searchHotels")
                .param("cityName", "Pune")
                .param("fromDate","2021-01-12")
                .param("toDate","2021-01-15")
                .param("roomType","SINGLE")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk());

    }
}