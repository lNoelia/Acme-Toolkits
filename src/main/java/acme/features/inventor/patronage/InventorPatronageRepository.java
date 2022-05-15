package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);
	
	@Query("select p.patron from Patronage p where p.patron.id = :patronId")
	Patron findPatronByPatronId(int patronId);
	
	@Query("select p from Patronage p where p.inventor.id = :inventorId AND p.draftMode = false")
	Collection<Patronage> findInventorPatronages(int inventorId);

	
}
