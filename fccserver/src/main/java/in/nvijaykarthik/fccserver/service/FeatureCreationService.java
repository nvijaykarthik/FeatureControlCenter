package in.nvijaykarthik.fccserver.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nvijaykarthik.fccserver.entity.FeatureActivationConfig;
import in.nvijaykarthik.fccserver.entity.FeatureServiceXref;
import in.nvijaykarthik.fccserver.entity.Features;
import in.nvijaykarthik.fccserver.repository.FeatureActivationConfigRepo;
import in.nvijaykarthik.fccserver.repository.FeatureServiceXrefRepo;
import in.nvijaykarthik.fccserver.repository.FeaturesRepo;

@Service
public class FeatureCreationService {
    
    @Autowired
    FeatureServiceXrefRepo featureServiceXrefRepo;
    @Autowired
    FeatureActivationConfigRepo activationConfigRepo;
    @Autowired
    FeaturesRepo featuresRepo;
    
    public Features saveFeature(Features features){
        return featuresRepo.save(features);
    }

    public FeatureServiceXref addImpactedService(FeatureServiceXref features){
        features.setCreatedDate(LocalDateTime.now());
        return featureServiceXrefRepo.save(features);
    }

    public void removeImpactedService(FeatureServiceXref features){
        featureServiceXrefRepo.deleteById(features.getId());
    }

    public FeatureActivationConfig saveActiveConfguration(FeatureActivationConfig features){
        features.setCreatedDate(LocalDateTime.now());
        return activationConfigRepo.save(features);
    }
}
