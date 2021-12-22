package org.nvijaykarthik.fccclient.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nvijaykarthik.fccclient.actionservice.FeatureActionProcessingFactory;
import org.nvijaykarthik.fccclient.dto.FeatureActivationConfig;
import org.nvijaykarthik.fccclient.dto.FeatureActivationConfigList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class FccClientService {

	@Value("${fccserver.url}")
	private String serverUrl;

	@Value("${fccserver.config.feature.url}")
	private String getFeatureConfigUrl;

	@Value("${fccserver.config.service.url}")
	private String getServiceConfigUrl;

	@Value("${fccserver.config.feature.service.url}")
	private String getFeatureServiceConfigUrl;

	public boolean getFeatureConfigByFeatureName(String featureName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("featureName", featureName);
		return isFeatureServiceActive(getConfig(serverUrl + getFeatureConfigUrl, params));
	}

	private FeatureActivationConfig getConfig(String url, Map<String, String> params) {
		return new RestTemplate().getForObject(url, FeatureActivationConfig.class, params);
	}

	private List<FeatureActivationConfig> getConfigs(String url, Map<String, String> params) {
		ResponseEntity<FeatureActivationConfig[]> responseEntity = new RestTemplate().getForEntity(url,
				FeatureActivationConfig[].class, params);
		return Arrays.asList(responseEntity.getBody());
	}

	private boolean isFeatureServiceActive(FeatureActivationConfig config) {
		FeatureActionProcessingFactory factory = new FeatureActionProcessingFactory();
		return factory.process(config);
	}

	public boolean getFeatureConfigByServiceName(String serviceName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("serviceName", serviceName);
		List<FeatureActivationConfig> configs = getConfigs(serverUrl + getServiceConfigUrl, params);
		return !CollectionUtils.isEmpty(configs) ? isFeatureServiceActive(configs.get(0)) : false;
	}

	public boolean getFeatureConfigByFeatureAndServiceName(String featureName, String serviceName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("featureName", featureName);
		params.put("serviceName", serviceName);
		return isFeatureServiceActive(getConfig(serverUrl + getFeatureServiceConfigUrl, params));
	}

}
