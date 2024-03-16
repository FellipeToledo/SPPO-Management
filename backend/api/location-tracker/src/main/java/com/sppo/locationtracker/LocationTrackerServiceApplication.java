package com.sppo.locationtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LocationTrackerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationTrackerServiceApplication.class, args);
	}

}
