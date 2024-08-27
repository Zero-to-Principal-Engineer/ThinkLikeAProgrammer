package com.skool.zerotoprincipal.ThinkLikeAProgrammer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ThinkLikeAProgrammerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void applicationStarts() {
		// This test checks if the application starts successfully.
		ThinkLikeAProgrammerApplication.main(new String[] {});
		assertTrue(true, "Application started successfully");
	}

}
