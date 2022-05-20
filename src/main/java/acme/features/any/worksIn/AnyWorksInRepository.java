package acme.features.any.worksIn;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Toolkit;
import acme.entities.worksIn.WorksIn;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyWorksInRepository extends AbstractRepository {
	
	@Query("select tk from Toolkit tk where tk.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select w from WorksIn w where w.toolkit.id = :id and amount>0")
	Collection<WorksIn> findWorksInsByToolkitId(int id);
	
	@Query("select w from WorksIn w where w.id = :id")
	WorksIn findOneWorksInById(int id);
	
}
