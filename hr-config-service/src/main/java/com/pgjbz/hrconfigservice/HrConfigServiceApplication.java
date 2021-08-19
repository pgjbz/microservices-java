package com.pgjbz.hrconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class HrConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrConfigServiceApplication.class, args);
	}

}
