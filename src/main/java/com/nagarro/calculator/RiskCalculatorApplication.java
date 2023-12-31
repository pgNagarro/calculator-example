package com.nagarro.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RiskCalculatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(RiskCalculatorApplication.class, args);
	}
}
