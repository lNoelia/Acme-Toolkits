package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.published = true")
	Collection<Toolkit> findPublishedToolkits();

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);

}
