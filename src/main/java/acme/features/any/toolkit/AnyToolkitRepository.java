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

	@Query("select w.toolkit from WorksIn w inner join w.artefact a"
		+ " where (a.name like :keyword or a.code like :keyword"
		+ " or a.technology like :keyword or a.description like :keyword)"
		+ " and w.toolkit.published = true")
	Collection<Toolkit> findPublishedToolkitsByArtefactKeyword(String keyword);
}
