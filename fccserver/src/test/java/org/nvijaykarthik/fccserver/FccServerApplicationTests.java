package org.nvijaykarthik.fccserver;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.nvijaykarthik.fccserver.actionservice.FCCActionProcessor;
import org.nvijaykarthik.fccserver.actionservice.FccScheduledActionProcessor;

class FccServerApplicationTests {



	@Test
	void contextLoads() {
		 FCCActionProcessor actionProcessor = new FccScheduledActionProcessor();
		 String json ="{\"startdatetime\":\"2021-11-20 05:00:00\",\"enddatetime\":\"2021-11-30 06:00:00\",\"status\":\"Y\"}";
		 assertTrue(actionProcessor.processAction(json));
	}

}
