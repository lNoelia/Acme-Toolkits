package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Integer totalNumberOfProposedPatronages;
    Integer totalNumberOfAcceptedPatronages;
    Integer totalNumberOfDeniedPatronages;
    Money averageBudgetOfProposedPatronagesByCurrency;
    Money averageBudgetOfAcceptedPatronagesByCurrency;
    Money averageBudgetOfDeniedPatronagesByCurrency;
    Money deviationBudgetOfProposedPatronagesByCurrency;
    Money deviationBudgetOfAcceptedPatronagesByCurrency;
    Money deviationBudgetOfDeniedPatronagesByCurrency;
    Money minimumBudgetOfProposedPatronagesByCurrency;
    Money minimumBudgetOfAcceptedPatronagesByCurrency;
    Money minimumBudgetOfDeniedPatronagesByCurrency;
    Money maximumBudgetOfProposedPatronagesByCurrency;
    Money maximumBudgetOfAcceptedPatronagesByCurrency;
    Money maximumBudgetOfDeniedPatronagesByCurrency;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
		

}
