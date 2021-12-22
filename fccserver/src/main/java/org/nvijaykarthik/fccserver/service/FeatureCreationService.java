package org.nvijaykarthik.fccserver.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.nvijaykarthik.fccserver.entity.FeatureActivationConfig;
import org.nvijaykarthik.fccserver.entity.FeatureServiceXref;
import org.nvijaykarthik.fccserver.entity.Features;
import org.nvijaykarthik.fccserver.repository.FeatureActivationConfigRepo;
import org.nvijaykarthik.fccserver.repository.FeatureServiceXrefRepo;
import org.nvijaykarthik.fccserver.repository.FeaturesRepo;

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
