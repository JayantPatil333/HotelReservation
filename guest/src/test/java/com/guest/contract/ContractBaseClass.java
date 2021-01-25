package com.guest.contract;

import com.guest.controller.IGuestController;
import com.guest.controller.implementation.GuestController;

import com.guest.filters.RequestFilter;
import com.guest.jwt.JwtUtil;
import io.restassured.config.EncoderConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@WithMockUser(username = "Guest", password = "password", roles = {"GUEST"})
public class ContractBaseClass {

    private IGuestController controller =  new GuestController();

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        EncoderConfig encoderConfig = new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig().encoderConfig(encoderConfig);
        RestAssuredMockMvc.standaloneSetup(this.controller);
        RestAssuredMockMvc.webAppContextSetup(applicationContext);
        /*mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        RestAssuredMockMvc.mockMvc(mockMvc);*/

    }
}
