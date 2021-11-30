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
        List<FeatureActivationConfig> configs= activationConfigRepo.findByFeatureName(featureName);
        if(CollectionUtils.isEmpty(configs)){
            return false;
        }
        FeatureActivationConfig config=configs.get(0);
       return actionProcessingService.process(config);
    }
    
}
