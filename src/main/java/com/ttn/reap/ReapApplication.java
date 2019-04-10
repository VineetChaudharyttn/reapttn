package com.ttn.reap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.ttn.reap.entity")
@EnableScheduling
public class  ReapApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReapApplication.class, args);
	}

}
