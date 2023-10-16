package com.java.bytes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("test")
public class TestWebSecurityConfigurer {


	@Bean
	InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.withUsername("test").password(passwordEncoder.encode("password")).roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().and().csrf().ignoringAntMatchers("/h2-console/").disable()
				.authorizeRequests().antMatchers("/h2-console/**")
				.permitAll().antMatchers("/patient/**")
				.authenticated().and()
				.httpBasic();

		return http.build();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}