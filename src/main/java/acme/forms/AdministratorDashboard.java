package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		int 									totalNumberOfComponents;
		Map<Pair<String,String>,Double>			averageRetailPriceOfComponentsByTechnologyAndCurrency;
		Map<Pair<String,String>,Double> 		deviationRetailPriceOfComponentsByTechnologyAndCurrency;
		Map<Pair<String,String>,Double> 		minimumRetailPriceOfComponentsByTechnologyAndCurrency;
		Map<Pair<String,String>,Double> 		maximumRetailPriceOfComponentsByTechnologyAndCurrency;

		int 									totalNumberOfTools;
		Map<String,Double> 						averageRetailPriceOfToolsByCurrency;
		Map<String,Double> 						deviationRetailPriceOfToolsByCurrency;
		Map<String,Double> 						minimumRetailPriceOfToolsByCurrency;
		Map<String,Double> 						maximumRetailPriceOfToolsByCurrency;
		
		Map<String,Integer> 					totalNumberOfPatronagesByStatus;
		Map<String,Double> 						averageBudgetOfPatronagesByStatus;
		Map<String,Double> 						deviationBudgetOfPatronagesByStatus;
		Map<String,Double> 						minimumBudgetOfPatronagesByStatus;
		Map<String,Double> 						maximumBudgetOfPatronagesByStatus;

		
		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------

}
