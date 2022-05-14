package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.patron.id = :id")
	Collection<Patronage> findAllPatronagesByPatronId(int id);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("select p from Patronage p where p.code = :code")
	Patronage findOnePatronageByCode(String code);
	
	@Query("select p from Patron p where p.userAccount.id = :id")
	Patron findOnePatronByUserId(int id);
	
	@Query("select i from Inventor i where i.userAccount.username = :username")
	Inventor findOneInventorByUsername(String username);
	
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
    String findAcceptedCurrencies();

	@Query("select i from Inventor i")
	Collection<Inventor> findAllInventors();

	@Query("select i from Inventor i where i.id = :id")
	Inventor findInventorById(int id);
}
