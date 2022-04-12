package acme.features.inventor.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorArtefactRepository extends AbstractRepository {
	
	@Query("select a from Artefact  a where a.inventor.id= :id")
	Collection<Artefact> findAllArtefactByInventorId(int id);
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findOneArtefactById(int id);

	@Query("select a from Artefact a where a.inventor.id = :inventorId and a.id in (select wi.artefact.id from WorksIn wi where wi.toolkit.id = :masterId)")
	Collection<Artefact> findAllArtefactByInventorPerToolkitId(int masterId, int inventorId);
}
