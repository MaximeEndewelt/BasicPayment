package com.payment.pollen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.payment.pollen")
public class PollenApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollenApplication.class, args);
	}
}
