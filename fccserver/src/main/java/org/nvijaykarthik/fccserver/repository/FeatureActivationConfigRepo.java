package org.nvijaykarthik.fccserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.nvijaykarthik.fccserver.entity.FeatureActivationConfig;

@Repository
public interface FeatureActivationConfigRepo extends CrudRepository<FeatureActivationConfig, Long> {

	FeatureActivationConfig findByFeatureName(String featureName);

	@Query("SELECT fac FROM FeatureActivationConfig fac, FeatureServiceXref fsx WHERE fsx.featureName = fac.featureName AND fsx.serviceName = :serviceName")
	List<FeatureActivationConfig> findActivationByServiceName(@Param("serviceName") String serviceName);

	@Query("SELECT fac FROM FeatureActivationConfig fac, FeatureServiceXref fsx WHERE fsx.featureName = fac.featureName AND fsx.serviceName = :serviceName AND fsx.featureName = :featureName")
	FeatureActivationConfig findActivationByServiceNameAndFeatureName(@Param("featureName") String featureName, @Param("serviceName") String serviceName);

}