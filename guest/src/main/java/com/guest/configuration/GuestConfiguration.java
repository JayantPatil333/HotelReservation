package com.guest.configuration;

import com.guest.mapper.IMapper;
import com.guest.mapper.implementation.Mapper;
import com.guest.service.GuestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuestConfiguration {
    @Bean
    public GuestService getGuestService(){
        return new GuestService();
    }
    @Bean
    public IMapper getMapper (){return new Mapper();
    }
}
