package org.nvijaykarthik.fccclient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import org.nvijaykarthik.fccclient.actionservice.FCCActionProcessor;
import org.nvijaykarthik.fccclient.actionservice.FccScheduledActionProcessor;

class FccClientServiceTest {
	
	private static final String serverUrl = "http://localhost:9000";

	@Test
	void loadActionProcessors() {
		FCCActionProcessor actionProcessor = new FccScheduledActionProcessor();
		String json = "{\"cron\":\"0 15 10 ? * 6L 2002-2005\",\"status\":\"Y\"}";
		assertFalse(actionProcessor.processAction(json));
	}
	
	//The below integration tests requires the server to be running
	
	@Test
	void getFeatureConfigByFeatureName() throws IOException, InterruptedException {
		String featureName = "Test Feature3";
		boolean isFeatureActive = new FccClientService().getFeatureConfigByFeatureName(serverUrl, featureName);
		assertFalse(isFeatureActive);
	}
	
	@Test
	void getFeatureConfigByServiceName() throws IOException, InterruptedException {
		String serviceName = "Test Service";
		boolean isFeatureActive = new FccClientService().getFeatureConfigByServiceName(serverUrl, serviceName);
		assertTrue(isFeatureActive);
	}
		
	@Test
	void getFeatureConfigByFeatureAndServiceName() throws IOException, InterruptedException {
		String featureName = "Test Feature";
		String serviceName = "Test Service";
		boolean isFeatureActive = new FccClientService().getFeatureConfigByFeatureAndServiceName(serverUrl, featureName, serviceName);
		assertTrue(isFeatureActive);
	}

}
