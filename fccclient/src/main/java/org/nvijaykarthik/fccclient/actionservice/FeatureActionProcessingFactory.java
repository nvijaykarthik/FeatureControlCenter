package org.nvijaykarthik.fccclient.actionservice;

import java.util.HashMap;
import java.util.Map;

import org.nvijaykarthik.fccclient.dto.FeatureActivationConfig;

public class FeatureActionProcessingFactory {

	private Map<String, FCCActionProcessor> actionMap = new HashMap<>();

	public FeatureActionProcessingFactory() {
		actionMap.put("ALWAYS", new FccAlwaysActionProcessor());
		actionMap.put("PERIOD", new FccPeriodActionProcessor());
		actionMap.put("SCHEDULED", new FccScheduledActionProcessor());
		actionMap.put("OND-TRAFFIC", new FccOnDemandTrafficActionProcessor());
		actionMap.put("OND-PRIORITY", new FccOnDemandPriorityActionProcessor());
	}

	public boolean process(FeatureActivationConfig config) {
		boolean activeFlag = false;
		FCCActionProcessor processor = actionMap.get(config.getAction());
		activeFlag = processor.processAction(config.getConfiguration());
		return activeFlag;
	}

}
