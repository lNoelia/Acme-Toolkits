package acme.features.patron.dashboard;

import org.springframework.stereotype.Repository;

import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository{
	
	// total number of proposed/accepted/denied patronages
	
	Integer totalNumberOfProposedPatronages();
	
	Integer totalNumberOfAcceptedPatronages();
	
	Integer totalNumberOfDeniedPatronages();
	
	// average budget of proposed/accepted/denied patronages grouped by currency.
	
	Money averageBudgetOfProposedPatronagesByCurrency();
	
	Money averageBudgetOfAcceptedPatronagesByCurrency();
	
	Money averageBudgetOfDeniedPatronagesByCurrency();
	
	// deviation budget of proposed/accepted/denied patronages grouped by currency.
	
	Money deviationBudgetOfProposedPatronagesByCurrency();
	
	Money deviationBudgetOfAcceptedPatronagesByCurrency();
	
	Money deviationBudgetOfDeniedPatronagesByCurrency();
	
	// minimum budget of proposed/accepted/denied patronages grouped by currency.

	Money minimumBudgetOfProposedPatronagesByCurrency();
	
	Money minimumBudgetOfAcceptedPatronagesByCurrency();
	
	Money minimumBudgetOfDeniedPatronagesByCurrency();
	
	// maximum budget of proposed/accepted/denied patronages grouped by currency.

	Money maximumBudgetOfProposedPatronagesByCurrency();
	
	Money maximumBudgetOfAcceptedPatronagesByCurrency();
	
	Money maximumBudgetOfDeniedPatronagesByCurrency();
}
