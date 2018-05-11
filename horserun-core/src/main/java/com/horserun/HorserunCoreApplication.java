package com.horserun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("${dubbo.config.location}")
public class HorserunCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorserunCoreApplication.class, args);
	}
}
