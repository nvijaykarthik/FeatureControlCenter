package in.nvijaykarthik.fccserver.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.nvijaykarthik.fccserver.entity.FeatureServiceXref;

@Repository
public interface FeatureServiceXrefRepo extends CrudRepository<FeatureServiceXref,Long>{

    List<FeatureServiceXref> findByFeatureName(String featureName);
    
}
