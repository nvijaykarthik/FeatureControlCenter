package in.nvijaykarthik.fccserver.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.nvijaykarthik.fccserver.entity.FeatureActivationConfig;
import in.nvijaykarthik.fccserver.entity.FeatureServiceXref;
import in.nvijaykarthik.fccserver.entity.Features;
import in.nvijaykarthik.fccserver.repository.FeatureActivationConfigRepo;
import in.nvijaykarthik.fccserver.repository.FeatureServiceXrefRepo;
import in.nvijaykarthik.fccserver.repository.FeaturesRepo;

@Service
public class FeatureListService {

    private static final Logger logger = LoggerFactory.getLogger(FeatureListService.class);

    @Autowired
    FeatureServiceXrefRepo featureServiceXrefRepo;
    @Autowired
    FeatureActivationConfigRepo activationConfigRepo;
    @Autowired
    FeaturesRepo featuresRepo;

    public Iterable<Features> getFeatures(Integer page, Integer size){
        if(null==size){
            size=25;
        }
        Sort sort = Sort.by("featureName").ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Iterable<Features> features= featuresRepo.findAll(pageable);
        logger.debug("Getting features {}",features);
        return features;
    }

    public List<FeatureServiceXref> getImpactedServices(String featureName){
        List<FeatureServiceXref> impactedService= featureServiceXrefRepo.findByFeatureName(featureName);
        logger.debug("Impacted service {}",impactedService);
        return impactedService;
    }

    public List<FeatureActivationConfig> getFeatureActivationConfig(String featureName){
        List<FeatureActivationConfig> activationConfig= activationConfigRepo.findByFeatureName(featureName);
        logger.debug("Impacted service {}",activationConfig);
        return activationConfig;
    }

}
