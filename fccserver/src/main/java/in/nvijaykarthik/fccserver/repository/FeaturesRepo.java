package in.nvijaykarthik.fccserver.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.nvijaykarthik.fccserver.entity.Features;

@Repository
public interface FeaturesRepo extends PagingAndSortingRepository<Features,String>{
    
}
