package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{

	@Query("select tk from Toolkit tk where tk.id in (select wi.toolkit.id from WorksIn wi join Artefact a on wi.artefact.id = a.id where a.inventor.id = :id)")
	Collection<Toolkit> findAllToolkitByInventorId(int id);
	
	@Query("select tk from Toolkit tk where tk.id = :id and tk.id in (select wi.toolkit.id from WorksIn wi join Artefact a on wi.artefact.id = a.id where a.inventor.id = :inventorId)")
	Toolkit findOneToolkitById(int id, int inventorId);
	
}
