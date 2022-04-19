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

import java.util.HashMap;
import java.util.Map;

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
		
		final Map<String,Double> averageBudgetOfPatronagesProposedByCurrency = this.repository.averageBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.PROPOSED);
		averageBudgetOfPatronagesProposedByCurrency.keySet().stream()
															.forEach( k -> averageBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.PROPOSED.toString(), k),averageBudgetOfPatronagesProposedByCurrency.get(k)));
		
		final Map<String,Double> averageBudgetOfPatronagesAcceptedByCurrency = this.repository.averageBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.ACCEPTED);
		averageBudgetOfPatronagesAcceptedByCurrency.keySet().stream()
															.forEach( k -> averageBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.ACCEPTED.toString(), k),averageBudgetOfPatronagesAcceptedByCurrency.get(k)));
		
		final Map<String,Double> averageBudgetOfPatronagesDeniedByCurrency = this.repository.averageBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.DENIED);
		averageBudgetOfPatronagesDeniedByCurrency.keySet().stream()
															.forEach( k -> averageBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.DENIED.toString(), k),averageBudgetOfPatronagesDeniedByCurrency.get(k)));
		
		final Map<String,Double> deviationBudgetOfPatronagesProposedByCurrency = this.repository.deviationBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.PROPOSED);
		deviationBudgetOfPatronagesProposedByCurrency.keySet().stream()
															.forEach( k -> deviationBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.PROPOSED.toString(), k),deviationBudgetOfPatronagesProposedByCurrency.get(k)));
		
		final Map<String,Double> deviationBudgetOfPatronagesAcceptedByCurrency = this.repository.deviationBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.ACCEPTED);
		deviationBudgetOfPatronagesAcceptedByCurrency.keySet().stream()
															.forEach( k -> deviationBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.ACCEPTED.toString(), k),deviationBudgetOfPatronagesAcceptedByCurrency.get(k)));
		
		final Map<String,Double> deviationBudgetOfPatronagesDeniedByCurrency = this.repository.deviationBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.DENIED);
		deviationBudgetOfPatronagesDeniedByCurrency.keySet().stream()
															.forEach( k -> deviationBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.DENIED.toString(), k),deviationBudgetOfPatronagesDeniedByCurrency.get(k)));
		

		final Map<String,Double> minimumBudgetOfPatronagesProposedByCurrency = this.repository.minimumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.PROPOSED);
		minimumBudgetOfPatronagesProposedByCurrency.keySet().stream()
															.forEach( k -> minimumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.PROPOSED.toString(), k),minimumBudgetOfPatronagesProposedByCurrency.get(k)));
		
		final Map<String,Double> minimumBudgetOfPatronagesAcceptedByCurrency = this.repository.minimumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.ACCEPTED);
		minimumBudgetOfPatronagesAcceptedByCurrency.keySet().stream()
															.forEach( k -> minimumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.ACCEPTED.toString(), k),minimumBudgetOfPatronagesAcceptedByCurrency.get(k)));
		
		final Map<String,Double> minimumBudgetOfPatronagesDeniedByCurrency = this.repository.minimumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.DENIED);
		minimumBudgetOfPatronagesDeniedByCurrency.keySet().stream()
															.forEach( k -> minimumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.DENIED.toString(), k),minimumBudgetOfPatronagesDeniedByCurrency.get(k)));
		
		final Map<String,Double> maximumBudgetOfPatronagesProposedByCurrency = this.repository.maximumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.PROPOSED);
		maximumBudgetOfPatronagesProposedByCurrency.keySet().stream()
															.forEach( k -> maximumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.PROPOSED.toString(), k),maximumBudgetOfPatronagesProposedByCurrency.get(k)));
		
		final Map<String,Double> maximumBudgetOfPatronagesAcceptedByCurrency = this.repository.maximumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.ACCEPTED);
		maximumBudgetOfPatronagesAcceptedByCurrency.keySet().stream()
															.forEach( k -> maximumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.ACCEPTED.toString(), k),maximumBudgetOfPatronagesAcceptedByCurrency.get(k)));
		
		final Map<String,Double> maximumBudgetOfPatronagesDeniedByCurrency = this.repository.maximumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.DENIED);
		maximumBudgetOfPatronagesDeniedByCurrency.keySet().stream()
															.forEach( k -> maximumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.DENIED.toString(), k),maximumBudgetOfPatronagesDeniedByCurrency.get(k)));
		
		
		
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
			"minimumBudgetOfPatronagesByStatusAndCurrency","maximumBudgetOfPatronagesByStatusAndCurrency"//
			);
	}

}
