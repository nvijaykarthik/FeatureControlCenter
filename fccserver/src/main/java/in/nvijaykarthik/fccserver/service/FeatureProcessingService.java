package in.nvijaykarthik.fccserver.service;

import java.util.List;

import org.springframework.util.CollectionUtils;

import in.nvijaykarthik.fccserver.actionservice.FeatureActionProcessingFactory;
import in.nvijaykarthik.fccserver.entity.FeatureActivationConfig;
import in.nvijaykarthik.fccserver.repository.FeatureActivationConfigRepo;

public class FeatureProcessingService {
   
    FeatureActivationConfigRepo activationConfigRepo;
    FeatureActionProcessingFactory actionProcessingService;
    
    public FeatureProcessingService(FeatureActivationConfigRepo activationConfigRepo){
        this.actionProcessingService=new FeatureActionProcessingFactory();
        this.activationConfigRepo=activationConfigRepo;
    }

    public boolean process(String featureName) {
       FeatureActivationConfig config= activationConfigRepo.findByFeatureName(featureName);
       return actionProcessingService.process(config);
    }
    
}
