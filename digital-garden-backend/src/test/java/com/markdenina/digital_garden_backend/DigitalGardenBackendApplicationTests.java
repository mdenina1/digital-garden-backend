package com.markdenina.digital_garden_backend;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalGardenBackendApplicationTests {

	@BeforeAll
	void printEnvVars() {
		System.out.println("DB_USERNAME = " + System.getenv("DB_USERNAME"));
		System.out.println("DB_PASSWORD = " + System.getenv("DB_PASSWORD"));
	}

	@Test
	void contextLoads() {
		// your normal test here
		System.out.println("DB_USERNAME = " + System.getenv("DB_USERNAME"));
		System.out.println("DB_PASSWORD = " + System.getenv("DB_PASSWORD"));
	}

}
