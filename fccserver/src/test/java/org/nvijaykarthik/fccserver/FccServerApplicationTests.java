package org.nvijaykarthik.fccserver;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FccServerApplicationTests {

	@Test
	//TODO: Add junits for services. For now dummy assertion
	void contextLoads() {
		String json = "{\"startdatetime\":\"2021-11-20 05:00:00\",\"enddatetime\":\"2021-11-30 06:00:00\",\"status\":\"Y\"}";
		assertNotNull(json);
	}

}
