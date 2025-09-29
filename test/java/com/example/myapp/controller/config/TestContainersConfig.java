package com.example.myapp.controller.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@TestConfiguration()
public class TestContainersConfig {
	
	@Container
	public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
			.withDatabaseName("mydatabase")
			.withUsername("myuser")
			.withPassword("mypassword");
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}
}
