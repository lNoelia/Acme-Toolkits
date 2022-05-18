
package acme.features.inventor.worksIn;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.entities.toolkits.Toolkit;
import acme.entities.worksIn.WorksIn;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorWorksInRepository extends AbstractRepository {

	@Query("select wk from WorksIn wk where wk.toolkit.id =:id")
	Collection<WorksIn> findAllWorksInByToolkit(int id);

	@Query("select wk from WorksIn wk where wk.id =:id")
	WorksIn findOneWorksInById(int id);

	@Query("select t from Toolkit t where t.id =:id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select a from Artefact a where a.id= :id")
	Artefact findOneArtefactById(int id);

	@Query("select a from Artefact a where a.published is true and a.id not in(select wk.artefact.id from WorksIn wk where wk.toolkit.id =:id)")
	Collection<Artefact> findArtefactsForToolkit(int id);
	
	@Query("select wk.artefact.id from WorksIn wk where wk.toolkit.id =:id")
	Collection<Integer> findArtefactsIdByToolkit(int id);
	

}
