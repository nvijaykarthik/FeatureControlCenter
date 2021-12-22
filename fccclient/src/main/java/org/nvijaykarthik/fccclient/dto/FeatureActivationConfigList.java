package org.nvijaykarthik.fccclient.dto;

import java.util.ArrayList;
import java.util.List;

public class FeatureActivationConfigList {
	private List<FeatureActivationConfig> featureActivationConfigs;

	public FeatureActivationConfigList() {
		featureActivationConfigs = new ArrayList<>();
	}

	public List<FeatureActivationConfig> getFeatureActivationConfigs() {
		return featureActivationConfigs;
	}

}
