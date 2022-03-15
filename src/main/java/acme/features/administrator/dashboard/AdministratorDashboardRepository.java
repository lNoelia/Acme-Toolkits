package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	//total number of components ---------------------------------------------------------------------------------------------
	@Query("select count(c) from Component c")
	Integer totalNumberOfComponents();

	
	//average retail price of components, grouped by technology and currency -------------------------------------------------
	@Query("select c.retailPrice.currency, avg( c.retailPrice.amount ) from Component c group by c.technology")
	Money averageRetailPriceOfComponentsByTechnology();
	
	@Query("select c.retailPrice.currency, avg( c.retailPrice.amount ) from Component c group by c.retailPrice.currency")
	Money averageRetailPriceOfComponentsByCurrency();
	
	
	//deviation retail price of components, grouped by technology and currency -----------------------------------------------
	@Query("select c.retailPrice.currency, stddev( c.retailPrice.amount ) from Component c group by c.technology")
	Money deviationRetailPriceOfComponentsByTechnology();
	
	@Query("select c.retailPrice.currency, stddev( c.retailPrice.amount ) from Component c group by c.retailPrice.currency")
	Money deviationRetailPriceOfComponentsByCurrency();
	
	
	//minimum retail price of components, grouped by technology and currency -------------------------------------------------
	@Query("select c.retailPrice.currency, min( c.retailPrice.amount ) from Component c group by c.technology")
	Money minimumRetailPriceOfComponentsByTechnology();
	
	@Query("select c.retailPrice.currency, min( c.retailPrice.amount ) from Component c group by c.retailPrice.currency")
	Money minimumRetailPriceOfComponentsByCurrency();
	
	
	//maximum retail price of components, grouped by technology and currency -------------------------------------------------
	@Query("select c.retailPrice.currency, max( c.retailPrice.amount ) from Component c group by c.technology")
	Money maximumRetailPriceOfComponentsByTechnology();
	
	@Query("select c.retailPrice.currency, max( c.retailPrice.amount ) from Component c group by c.retailPrice.currency")
	Money maximumRetailPriceOfComponentsByCurrency();
	
	
	//total number of tools --------------------------------------------------------------------------------------------------
	@Query("select count(t) from Tool t")
	Integer totalNumberOfTools();
	
	
	//average retail price of tools, grouped by currency ---------------------------------------------------------------------
	@Query("select t.retailPrice.currency, avg( t.retailPrice.amount ) from Tool t group by t.retailPrice.currency")
	Money averageRetailPriceOfToolsByCurrency();
	
	
	//deviation retail price of tools, grouped by currency -------------------------------------------------------------------
	@Query("select t.retailPrice.currency, stddev( t.retailPrice.amount ) from Tool t group by t.retailPrice.currency")
	Money deviationRetailPriceOfToolsByCurrency();
	
	
	//minimum retail price of tools, grouped by currency ---------------------------------------------------------------------
	@Query("select t.retailPrice.currency, min( t.retailPrice.amount ) from Tool t group by t.retailPrice.currency")
	Money minimumRetailPriceOfToolsByCurrency();
	
	
	//maximum retail price of tools, grouped by currency ---------------------------------------------------------------------
	@Query("select t.retailPrice.currency, max( t.retailPrice.amount ) from Tool t group by t.retailPrice.currency")
	Money maximumRetailPriceOfToolsByCurrency();
	
	
	//total number of proposed/accepted/denied patronages --------------------------------------------------------------------
	@Query("select count(p) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.PROPOSED")
	Integer totalNumberOfProposedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.ACCEPTED")
	Integer totalNumberOfAcceptedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.DENIED")
	Integer totalNumberOfDeniedPatronages();
	
	
	//average, deviation, minimum, and maximum budget of proposed/accepted/denied patronages ---------------------------------
	@Query("select p.budget.currency, avg( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.PROPOSED")
	Money averageBudgetOfProposedPatronages();
	
	@Query("select p.budget.currency, avg( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.ACCEPTED")
	Money averageBudgetOfAcceptedPatronages();
	
	@Query("select p.budget.currency, avg( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.DENIED")
	Money averageBudgetOfDeniedPatronages();
	
	
	//deviation budget of proposed/accepted/denied patronages ----------------------------------------------------------------
	@Query("select p.budget.currency, stddev( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.PROPOSED")
	Money deviationBudgetOfProposedPatronages();
	
	@Query("select p.budget.currency, stddev( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.ACCEPTED")
	Money deviationBudgetOfAcceptedPatronages();
	
	@Query("select p.budget.currency, stddev( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.DENIED")
	Money deviationBudgetOfDeniedPatronages();
	
	
	//minimum budget of proposed/accepted/denied patronages ------------------------------------------------------------------
	@Query("select p.budget.currency, min( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.PROPOSED")
	Money minimumBudgetOfProposedPatronages();
	
	@Query("select p.budget.currency, min( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.ACCEPTED")
	Money minimumBudgetOfAcceptedPatronages();
	
	@Query("select p.budget.currency, min( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.DENIED")
	Money minimumBudgetOfDeniedPatronages();
	
	
	//maximum budget of proposed/accepted/denied patronages ------------------------------------------------------------------
	@Query("select p.budget.currency, max( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.PROPOSED")
	Money maximumBudgetOfProposedPatronages();
	
	@Query("select p.budget.currency, max( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.ACCEPTED")
	Money maximumBudgetOfAcceptedPatronages();
	
	@Query("select p.budget.currency, max( p.budget.amount ) from Patronage p where p.status = acme.entities.patronages.PatronageStatus.DENIED")
	Money maximumBudgetOfDeniedPatronages();
}
