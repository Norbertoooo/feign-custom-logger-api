package com.vitu.feign.custom.logger.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class FeignCustomLoggerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignCustomLoggerApiApplication.class, args);
	}

}
