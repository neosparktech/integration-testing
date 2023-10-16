package com.java.bytes.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AppTestConfiguration {

	@Bean
	TestRestTemplate configureTestRestRemplate() {
		return new TestRestTemplate("test", "password");
	}
}
