package org.nvijaykarthik.fccserver.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.nvijaykarthik.fccserver.entity.FeatureActivationConfig;
import org.nvijaykarthik.fccserver.entity.FeatureServiceXref;
import org.nvijaykarthik.fccserver.entity.Features;
import org.nvijaykarthik.fccserver.repository.FeatureActivationConfigRepo;
import org.nvijaykarthik.fccserver.repository.FeatureServiceXrefRepo;
import org.nvijaykarthik.fccserver.repository.FeaturesRepo;

@Service
public class FeatureListService {

	private static final Logger logger = LoggerFactory.getLogger(FeatureListService.class);

	@Autowired
	FeatureServiceXrefRepo featureServiceXrefRepo;
	@Autowired
	FeatureActivationConfigRepo activationConfigRepo;
	@Autowired
	FeaturesRepo featuresRepo;

	public Iterable<Features> getFeatures(Integer page, Integer size) {
		if (null == size) {
			size = 25;
		}
		Sort sort = Sort.by("featureName").ascending();
		Pageable pageable = PageRequest.of(page, size, sort);

		Iterable<Features> features = featuresRepo.findAll(pageable);
		logger.debug("Getting features {}", features);
		return features;
	}

	public Iterable<Features> getFeatures() {
		Iterable<Features> features = featuresRepo.findAll();
		logger.debug("Getting features {}", features);
		return features;
	}

	public List<FeatureServiceXref> getImpactedServices(String featureName) {
		List<FeatureServiceXref> impactedService = featureServiceXrefRepo.findByFeatureName(featureName);
		logger.debug("Impacted service {}", impactedService);
		return impactedService;
	}

	public FeatureActivationConfig getFeatureActivationConfig(String featureName) {
		FeatureActivationConfig activationConfig = activationConfigRepo.findByFeatureName(featureName);
		logger.debug("Action config {}", activationConfig);
		return activationConfig;
	}

	public List<FeatureActivationConfig> getServiceActivationConfig(String serviceName) {
		List<FeatureActivationConfig> serviceActivationConfigs = activationConfigRepo
				.findActivationByServiceName(serviceName);
		logger.debug("Service Activation config {}", serviceActivationConfigs);
		return serviceActivationConfigs;
	}
	
	public FeatureActivationConfig getServiceAndFeatureActivationConfig(String featureName, String serviceName) {
		FeatureActivationConfig serviceActivationConfig = activationConfigRepo
			.findActivationByServiceNameAndFeatureName(featureName, serviceName);
		logger.debug("Feature and Service Activation config {}", serviceActivationConfig);
		return serviceActivationConfig;
	}

}
