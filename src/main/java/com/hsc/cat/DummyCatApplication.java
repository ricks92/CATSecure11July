package com.hsc.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DummyCatApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyCatApplication.class, args);
	}
}
