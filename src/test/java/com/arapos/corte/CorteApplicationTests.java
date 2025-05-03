package com.arapos.corte;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("Disabled for Docker build - integration test requires DB")
@SpringBootTest
class CorteApplicationTests {

	@Test
	void contextLoads() {
	}

}
