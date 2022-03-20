package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Integer										totalNumberOfProposedPatronages;
    Integer										totalNumberOfAcceptedPatronages;
    Integer										totalNumberOfDeniedPatronages;
    Map<Pair<String, String>, Double>			averageBudgetOfPatronagesByStatusAndCurrency;
    Map<Pair<String, String>, Double>			deviationBudgetOfPatronagesByStatusAndCurrency;
    Map<Pair<String, String>, Double> 			minimumBudgetOfPatronagesByStatusAndCurrency;
    Map<Pair<String, String>, Double> 			maximumBudgetOfPatronagesByStatusAndCurrency;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
