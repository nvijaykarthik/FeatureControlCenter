package org.nvijaykarthik.fccserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.nvijaykarthik.fccserver.entity.FeatureActivationConfig;
import org.nvijaykarthik.fccserver.service.FeatureListService;

@RestController
@RequestMapping("/api")
public class FeatureActionController {

	@Autowired
	FeatureListService featureListService;

	@GetMapping("/getActivationConfigByService")
	public List<FeatureActivationConfig> getFeatureActivationConfigByService(@RequestParam String serviceName) {
		return featureListService.getServiceActivationConfig(serviceName);
	}

	@GetMapping("/getActivationConfigByFeature/{featureName}")
	public FeatureActivationConfig getFeatureActivationConfigByFeatureAndService(
			@PathVariable("featureName") String featureName, @RequestParam String serviceName) {
		return featureListService.getServiceAndFeatureActivationConfig(featureName, serviceName);
	}
}
