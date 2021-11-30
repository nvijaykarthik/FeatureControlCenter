package in.nvijaykarthik.fccserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.nvijaykarthik.fccserver.service.FeatureProcessingService;

@RestController
@RequestMapping("/api")
public class FeatureActionController {
    
    @Autowired
    FeatureProcessingService featureProcessingService; 

    @GetMapping("/isFeatureActive")
    public boolean isFeatureActive(@RequestParam(name = "featureName") String featureName){
        return featureProcessingService.process(featureName);
    }
}
