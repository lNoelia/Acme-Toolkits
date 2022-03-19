package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		Integer 			totalNumberOfComponents;
		Money 				averageRetailPriceOfComponentsByTechnology;
		Money 				averageRetailPriceOfComponentsByCurrency;
		Money 				deviationRetailPriceOfComponentsByTechnology;
		Money 				deviationRetailPriceOfComponentsByCurrency;
		Money 				minimumRetailPriceOfComponentsByTechnology;
		Money 				minimumRetailPriceOfComponentsByCurrency;
		Money 				maximumRetailPriceOfComponentsByTechnology;
		Money 				maximumRetailPriceOfComponentsByCurrency;
		Integer 			totalNumberOfTools;
		Money 				averageRetailPriceOfToolsByCurrency;
		Money 				deviationRetailPriceOfToolsByCurrency;
		Money 				minimumRetailPriceOfToolsByCurrency;
		Money 				maximumRetailPriceOfToolsByCurrency;
		Integer 			totalNumberOfProposedPatronages;
		Integer 			totalNumberOfAcceptedPatronages;
		Integer 			totalNumberOfDeniedPatronages;
		Money 				averageBudgetOfProposedPatronages;
		Money 				averageBudgetOfAcceptedPatronages;
		Money 				averageBudgetOfDeniedPatronages;
		Money 				deviationBudgetOfProposedPatronages;
		Money 				deviationBudgetOfAcceptedPatronages;
		Money 				deviationBudgetOfDeniedPatronages;
		Money 				minimumBudgetOfProposedPatronages;
		Money 				minimumBudgetOfAcceptedPatronages;
		Money 				minimumBudgetOfDeniedPatronages;
		Money 				maximumBudgetOfProposedPatronages;
		Money 				maximumBudgetOfAcceptedPatronages;
		Money 				maximumBudgetOfDeniedPatronages;
		
		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------

}
