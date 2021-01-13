package com.guest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guest.model.IGuest;
import com.guest.model.implementation.Guest;
import com.guest.service.IGuestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityNotFoundException;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private IGuestService guestService;

    private Logger LOGGER = LoggerFactory.getLogger(GuestControllerTest.class);

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
    //@WithMockUser("Guest")
    @Test
    public void getGuest_ShouldReturnGuest() throws Exception {
        given(guestService.getGuest(1L)).willReturn(guest);
        LOGGER.error("Inside method...");
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/1")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Jayant"));

    }

    //@WithMockUser("Guest")
    @Test
    public void getGuest_EntityNotFoundException() throws Exception {
        given(guestService.getGuest(anyLong())).willThrow(new EntityNotFoundException("Entity Not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/1")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isNotFound());

    }

    @WithMockUser("Guest")
    @Test
    public void addNewStay() throws Exception {
        given(guestService.addStayByGuest(anyLong(), any())).willReturn("Stay added successfully.");
        mockMvc.perform(MockMvcRequestBuilders.patch("/guest/addNewStay")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("guestId", "1")
                .param("reservationId","1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Stay added successfully."));
    }

    @WithMockUser("Guest")
    @Test
    public void addNewGuest() throws Exception {
        IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
        given(guestService.addNewGuest(any())).willReturn(guest);
        ObjectMapper mapper =  new ObjectMapper();
        String json = mapper.writeValueAsString(guest);
        mockMvc.perform(MockMvcRequestBuilders.post("/guest")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isCreated());

    }

    @Test
    public void getGuests() throws Exception {
        IGuest guest = new Guest(1L, "Jayant","jayant@gmail.com","1234567");
        given(guestService.getGuests(any())).willReturn(Arrays.asList(guest));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/guest/getGuests")
                .param("guestId", "1")
                .param("guestId", "2")
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Jayant"));
    }
}