package acme.features.any.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyArtefactRepository extends AbstractRepository {
	
	@Query("select a from Artefact a where a.published = true")
	Collection<Artefact> findAllPublishedArtefact();
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findOneArtefactById(int id);
	
	@Query("select tk from Toolkit tk where tk.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select w.artefact from WorksIn w inner join w.toolkit t where t.id = :id")
	Collection<Artefact> findArtefactsByToolkitId(int id);

}
