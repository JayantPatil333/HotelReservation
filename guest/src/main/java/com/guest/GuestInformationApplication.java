package com.guest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GuestInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestInformationApplication.class, args);
	}

}
