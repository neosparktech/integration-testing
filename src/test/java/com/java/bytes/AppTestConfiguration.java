package com.java.bytes;

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
