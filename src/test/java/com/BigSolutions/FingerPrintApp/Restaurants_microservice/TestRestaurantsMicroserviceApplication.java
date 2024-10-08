package com.BigSolutions.FingerPrintApp.Restaurants_microservice;

import org.springframework.boot.SpringApplication;

public class TestRestaurantsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(RestaurantsMicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
