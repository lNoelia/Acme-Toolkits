package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{

	@Query("select tk from Toolkit tk where tk.inventor.id = :id")
	Collection<Toolkit> findAllToolkitByInventorId(int id);
	
	@Query("select tk from Toolkit tk where tk.id = :id")
	Toolkit findOneToolkitById(int id);
	
}
