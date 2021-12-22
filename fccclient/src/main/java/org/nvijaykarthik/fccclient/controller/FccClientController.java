package org.nvijaykarthik.fccclient.controller;

import org.nvijaykarthik.fccclient.client.FccClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class FccClientController {

	@Autowired
	FccClientService fccClientService;

	@GetMapping("/getActivationConfig")
	public boolean getFeatureActivationConfig(@RequestParam String featureName, @RequestParam(required = false) String serviceName) {
		if (serviceName != null) {
			return fccClientService.getFeatureConfigByFeatureAndServiceName(featureName, serviceName);
		} else {
			return fccClientService.getFeatureConfigByFeatureName(featureName);
		}
	}

	@GetMapping("/getActivationConfigByService")
	public boolean getActivationConfigByService(@RequestParam String serviceName) {
		return fccClientService.getFeatureConfigByServiceName(serviceName);
	}

}
