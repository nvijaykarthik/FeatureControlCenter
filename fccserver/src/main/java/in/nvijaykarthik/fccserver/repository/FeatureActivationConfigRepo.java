package in.nvijaykarthik.fccserver.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.nvijaykarthik.fccserver.entity.FeatureActivationConfig;

@Repository
public interface FeatureActivationConfigRepo extends CrudRepository<FeatureActivationConfig,Long> {

    List<FeatureActivationConfig> findByFeatureName(String featureName);
    
}
