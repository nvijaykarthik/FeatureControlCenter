package in.nvijaykarthik.fccserver.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.parser.CronParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.nvijaykarthik.fccserver.entity.FeatureActivationConfig;
import in.nvijaykarthik.fccserver.entity.FeatureServiceXref;
import in.nvijaykarthik.fccserver.entity.Features;
import in.nvijaykarthik.fccserver.service.FeatureCreationService;
import in.nvijaykarthik.fccserver.service.FeatureListService;

@RestController
@RequestMapping("/api")
public class FeatureUiController {
    
    @Autowired
    FeatureCreationService featureCreationService;
    @Autowired
    FeatureListService featureListService; 
    @Autowired
    CronParser cronParser;

    private static final Logger log = LoggerFactory.getLogger(FeatureUiController.class);

    @GetMapping("/getFeaturesPaged")
    public Iterable<Features> getFeaturesPaged(@RequestParam Integer page, @RequestParam(required = false) Integer size){
        return featureListService.getFeatures(page, size);
    }

    @GetMapping("/getFeatures")
    public Iterable<Features> getFeatures(){
        return featureListService.getFeatures();
    }

    @GetMapping("/getImpactedServices")
    public List<FeatureServiceXref> getImpactedServices(@RequestParam String featureName){
        return featureListService.getImpactedServices(featureName);
    }

    @GetMapping("/getActivationConfig")
    public FeatureActivationConfig getFeatureActivationConfig(@RequestParam String featureName){
        return featureListService.getFeatureActivationConfig(featureName);
    }

    @PostMapping("/saveFeatures")
    public Features saveFeature(@RequestBody Features features){
        features.setCreatedDate(LocalDateTime.now());
        return featureCreationService.saveFeature(features);
    }

    @PostMapping("/addImpactedService")
    public FeatureServiceXref addImpactedService(@RequestBody  FeatureServiceXref features){
        return featureCreationService.addImpactedService(features);
    }

    @PostMapping("/removeImpactedService")
    public void removeImpactedService(@RequestBody FeatureServiceXref features){
        featureCreationService.removeImpactedService(features);
    }

    @PostMapping("/saveActivationConfig")
    public FeatureActivationConfig saveActiveConfguration(@RequestBody FeatureActivationConfig features){
        return featureCreationService.saveActiveConfguration(features);
 
    }

    @GetMapping("/evaluateCron")
    public String cronDescription(@RequestParam String cron) {
        CronDescriptor descriptor = CronDescriptor.instance();
        log.info("Incomming cron {}",cron);
        String description = descriptor.describe(cronParser.parse(cron));
        return description;
    }
}
