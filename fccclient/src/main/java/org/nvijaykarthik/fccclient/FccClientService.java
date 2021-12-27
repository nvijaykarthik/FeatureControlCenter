package org.nvijaykarthik.fccclient;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.nvijaykarthik.fccclient.actionservice.FeatureActionProcessingFactory;
import org.nvijaykarthik.fccclient.client.HttpService;
import org.nvijaykarthik.fccclient.dto.FeatureActivationConfig;
import org.nvijaykarthik.fccclient.utils.LoadConfigProperties;

public class FccClientService {

	private static final Properties configs = LoadConfigProperties.loadClientConfigs();
	
	public static void main(String[] args) {
		
	}

	private boolean isFeatureServiceActive(FeatureActivationConfig config) {
		FeatureActionProcessingFactory factory = new FeatureActionProcessingFactory();
		return factory.process(config);
	}

	/**
	 * Get Feature Config by feature name
	 * 
	 * @param url
	 * @param featureName
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Boolean getFeatureConfigByFeatureName(String url, String featureName)
			throws IOException, InterruptedException {
		String featureConfigUrl = url
				+ configs.getProperty("fccserver.config.feature.url").replace("{featureName}", featureName.replace(" ", "+"));
		FeatureActivationConfig config = new HttpService().sendGetRequest(featureConfigUrl,
				FeatureActivationConfig.class);
		return isFeatureServiceActive(config);
	}

	/**
	 * Get Feature Config by service name
	 * 
	 * @param url
	 * @param serviceName
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean getFeatureConfigByServiceName(String url, String serviceName)
			throws IOException, InterruptedException {
		String serviceConfigUrl = url
				+ configs.getProperty("fccserver.config.service.url").replace("{serviceName}", serviceName.replace(" ", "+"));
		List<FeatureActivationConfig> configs = new HttpService().sendGetListRequest(serviceConfigUrl,
				FeatureActivationConfig.class);
		return configs != null && !configs.isEmpty() ? isFeatureServiceActive(configs.get(0)) : false;
	}

	/**
	 * Get Feature Config by feature name and service name
	 * 
	 * @param url
	 * @param featureName
	 * @param serviceName
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean getFeatureConfigByFeatureAndServiceName(String url, String featureName, String serviceName)
			throws IOException, InterruptedException {
		String configUrl = url + configs.getProperty("fccserver.config.feature.service.url")
				.replace("{featureName}", HttpService.encodeUrlParameters(featureName)).replace("{serviceName}", HttpService.encodeUrlParameters(serviceName));
		FeatureActivationConfig config = new HttpService().sendGetRequest(configUrl, FeatureActivationConfig.class);
		return isFeatureServiceActive(config);
	}
}
