package acme.features.any.artefact;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.artefact.Artefact;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyArtefactRepository extends AbstractRepository {
	
	@Query("select a from Artefact a")
	Collection<Artefact> findAllArtefact();
	
	@Query("select a from Artefact a where a.id = :id")
	Artefact findOneArtefactById(int id);

}
