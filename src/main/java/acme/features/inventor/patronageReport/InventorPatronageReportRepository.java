package acme.features.inventor.patronageReport;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronageReport.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {
	
	@Query("select pr.patronage from PatronageReport pr where pr.patronage.id=:id")
	Patronage findPatronageReportById(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.inventor.id= :id")
	Collection<PatronageReport> findAllPatronageReportByInventorId(int id);
	
	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select pr.sequenceNumber from PatronageReport pr where pr.patronage.id = :id order by pr.sequenceNumber desc")
	List<String> findSequenceNumberByPatronageId(int id);

}
