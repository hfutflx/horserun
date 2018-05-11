package com.horserun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("${dubbo.config.location}")
public class HorserunUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorserunUserApplication.class, args);
	}
}
