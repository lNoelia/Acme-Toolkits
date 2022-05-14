package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{

	@Query("select tk from Toolkit tk where tk.inventor.id = :id")
	Collection<Toolkit> findAllToolkitByInventorId(int id);
	
	@Query("select tk from Toolkit tk where tk.id = :id")
	Toolkit findOneToolkitById(int id);

	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);

	@Query("select tk from Toolkit tk where tk.code = :code")
	Toolkit findOneToolkitByCode(String code);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
}
