/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.dashboard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	// AbstractShowService<Patron, PatronDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		final PatronDashboard result;
		int totalNumberOfProposedPatronages;
		int totalNumberOfAcceptedPatronages;
		int totalNumberOfDeniedPatronages;
		final Map<Pair<String, String>, Double> averageBudgetOfPatronagesByStatusAndCurrency = new HashMap<>();
		final Map<Pair<String, String>, Double> deviationBudgetOfPatronagesByStatusAndCurrency = new HashMap<>();
	    final Map<Pair<String, String>, Double> minimumBudgetOfPatronagesByStatusAndCurrency = new HashMap<>();
	    final Map<Pair<String, String>, Double> maximumBudgetOfPatronagesByStatusAndCurrency = new HashMap<>();

		
		totalNumberOfProposedPatronages=this.repository.totalNumberOfPatronagesByStatus(PatronageStatus.PROPOSED);
		totalNumberOfAcceptedPatronages=this.repository.totalNumberOfPatronagesByStatus(PatronageStatus.ACCEPTED);
		totalNumberOfDeniedPatronages= this.repository.totalNumberOfPatronagesByStatus(PatronageStatus.DENIED);
		
		final Collection<Tuple> averageBudgetOfPatronagesByStatusAndCurrencyCollection = this.repository.averageBudgetOfPatronagesByStatusAndCurrency();	
		averageBudgetOfPatronagesByStatusAndCurrencyCollection.stream()
													.forEach( t -> 
															averageBudgetOfPatronagesByStatusAndCurrency.put(Pair.of(t.get(0).toString(),t.get(1).toString()),Double.parseDouble(t.get(2).toString())));													
		
		final Collection<Tuple> deviationBudgetOfPatronagesByStatusAndCurrencyCollection = this.repository.deviationBudgetOfPatronagesByStatusAndCurrency();
		deviationBudgetOfPatronagesByStatusAndCurrencyCollection.stream()
															.forEach( t -> deviationBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(t.get(0).toString(),t.get(1).toString()),Double.parseDouble(t.get(2).toString())));
		
		final Collection<Tuple> minimumBudgetOfPatronagesByStatusAndCurrencyCollection = this.repository.minimumBudgetOfPatronagesByStatusAndCurrency();
		minimumBudgetOfPatronagesByStatusAndCurrencyCollection.stream()
															.forEach( t -> minimumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(t.get(0).toString(),t.get(1).toString()),Double.parseDouble(t.get(2).toString())));
		
		final Collection<Tuple> maximumBudgetOfPatronagesByStatusCurrencyCollection = this.repository.maximumBudgetOfPatronagesByStatusAndCurrency();
		maximumBudgetOfPatronagesByStatusCurrencyCollection.stream()
															.forEach( t -> maximumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(t.get(0).toString(),t.get(1).toString()),Double.parseDouble(t.get(2).toString())));
		
		
		result = new PatronDashboard();
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
		result.setAverageBudgetOfPatronagesByStatusAndCurrency(averageBudgetOfPatronagesByStatusAndCurrency);
		result.setDeviationBudgetOfPatronagesByStatusAndCurrency(deviationBudgetOfPatronagesByStatusAndCurrency);
		result.setMinimumBudgetOfPatronagesByStatusAndCurrency(minimumBudgetOfPatronagesByStatusAndCurrency);
		result.setMaximumBudgetOfPatronagesByStatusAndCurrency(maximumBudgetOfPatronagesByStatusAndCurrency);

		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		
		
		
		request.unbind(entity, model,"totalNumberOfProposedPatronages",
			"totalNumberOfAcceptedPatronages","totalNumberOfDeniedPatronages",
			"averageBudgetOfPatronagesByStatusAndCurrency","deviationBudgetOfPatronagesByStatusAndCurrency",
			"minimumBudgetOfPatronagesByStatusAndCurrency","maximumBudgetOfPatronagesByStatusAndCurrency"
			);
	}

}
