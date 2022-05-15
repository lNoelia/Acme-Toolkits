package acme.features.inventor.worksIn;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.worksIn.WorksIn;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorWorksInRepository extends AbstractRepository {
	
	@Query("select wk from WorksIn wk where wk.toolkit.id =:id")
	Collection<WorksIn> findAllWorksInByToolkit(int id);
	
	@Query("select wk from WorksIn wk where wk.id =:id")
	WorksIn findOneWorksInById(int id);
	
	
}
