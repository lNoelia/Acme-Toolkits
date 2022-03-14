package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	//total number of components
	
	//average, deviation, minimum, and maximum retail price of components, grouped by technology and currency
	
	//total number of tools
	
	//average, deviation, minimum, and maximum retail price of tools, grouped by currency

	//total number of proposed/accepted/denied patronages
	@Query("select count(p) from Patronage p where p.status = acme.entities.PatronageStatus.PROPOSED")
	Integer totalNumberOfProposedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.PatronageStatus.ACCEPTED")
	Integer totalNumberOfAcceptedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.PatronageStatus.DENIED")
	Integer totalNumberOfDeniedPatronages();
	
	//average, deviation, minimum, and maximum budget of proposed/accepted/denied patronages
	@Query("select avg( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.PROPOSED")
	Double averageBudgetOfProposedPatronages();
	
	@Query("select avg( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.ACCEPTED")
	Double averageBudgetOfAcceptedPatronages();
	
	@Query("select avg( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.DENIED")
	Double averageBudgetOfDeniedPatronages();
	
	//deviation budget of proposed/accepted/denied patronages
	@Query("select stddev( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.PROPOSED")
	Double deviationBudgetOfProposedPatronages();
	
	//minimum budget of proposed/accepted/denied patronages
	@Query("select min( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.PROPOSED")
	Double minimumBudgetOfProposedPatronages();
	
	@Query("select min( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.ACCEPTED")
	Double minimumBudgetOfAcceptedPatronages();
	
	@Query("select min( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.DENIED")
	Double minimumBudgetOfDeniedPatronages();
	
	//maximum budget of proposed/accepted/denied patronages
	@Query("select max( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.PROPOSED")
	Double maximumBudgetOfProposedPatronages();
	
	@Query("select max( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.ACCEPTED")
	Double maximumBudgetOfAcceptedPatronages();
	
	@Query("select max( p.budget ) from Patronage p where p.status = acme.entities.PatronageStatus.DENIED")
	Double maximumBudgetOfDeniedPatronages();
}
