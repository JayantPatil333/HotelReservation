package com.reservation.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.reservation.model.IGuest;
import com.reservation.model.implementation.Guest;
import com.reservation.response.ApiResponseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "com:guest:+:stubs:8081"
)
public class TestForStubbedServices {

    Logger LOGGER = LoggerFactory.getLogger(TestForStubbedServices.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetGuest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/reservations/guests/get/{id}", "1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(user("Guest")
                        .password("password")
                        .roles("GUEST")))
                .andExpect(status().isOk()).andReturn();
        Gson gson =  new Gson();
        ApiResponseImpl apiResponse = gson.fromJson(mvcResult.getResponse().getContentAsString(), ApiResponseImpl.class);
        IGuest iGuest = gson.fromJson(apiResponse.getActualResponse().toString(), Guest.class) ;

        assertTrue(apiResponse.getApiError() == null);
        assertTrue(iGuest.getGuestId().equals(1L));
        //assertTrue(iGuest.getContactNumber() != null);
    }

}
