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
		
		final Collection<Tuple> averageBudgetOfPatronagesProposedByCurrency = this.repository.averageBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.PROPOSED);
		
		
		averageBudgetOfPatronagesProposedByCurrency.stream()
													.forEach( t -> 
															averageBudgetOfPatronagesByStatusAndCurrency.put(Pair.of(PatronageStatus.PROPOSED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));													
		
		final Collection<Tuple> averageBudgetOfPatronagesAcceptedByCurrency = this.repository.averageBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.ACCEPTED);
		averageBudgetOfPatronagesAcceptedByCurrency.stream()
															.forEach( t -> averageBudgetOfPatronagesByStatusAndCurrency.put(Pair.of(PatronageStatus.ACCEPTED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> averageBudgetOfPatronagesDeniedByCurrency = this.repository.averageBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.DENIED);
		averageBudgetOfPatronagesDeniedByCurrency.stream()
															.forEach( t -> averageBudgetOfPatronagesByStatusAndCurrency.put(Pair.of(PatronageStatus.DENIED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> deviationBudgetOfPatronagesProposedByCurrency = this.repository.deviationBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.PROPOSED);
		deviationBudgetOfPatronagesProposedByCurrency.stream()
															.forEach( t -> deviationBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.PROPOSED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> deviationBudgetOfPatronagesAcceptedByCurrency = this.repository.deviationBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.ACCEPTED);
		deviationBudgetOfPatronagesAcceptedByCurrency.stream()
															.forEach( t -> deviationBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.ACCEPTED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> deviationBudgetOfPatronagesDeniedByCurrency = this.repository.deviationBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.DENIED);
		deviationBudgetOfPatronagesDeniedByCurrency.stream()
															.forEach( t -> deviationBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.DENIED.toString(), t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		

		final Collection<Tuple> minimumBudgetOfPatronagesProposedByCurrency = this.repository.minimumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.PROPOSED);
		minimumBudgetOfPatronagesProposedByCurrency.stream()
															.forEach( t -> minimumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.PROPOSED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> minimumBudgetOfPatronagesAcceptedByCurrency = this.repository.minimumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.ACCEPTED);
		minimumBudgetOfPatronagesAcceptedByCurrency.stream()
															.forEach( t -> minimumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.ACCEPTED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> minimumBudgetOfPatronagesDeniedByCurrency = this.repository.minimumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.DENIED);
		minimumBudgetOfPatronagesDeniedByCurrency.stream()
															.forEach( t -> minimumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.DENIED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> maximumBudgetOfPatronagesProposedByCurrency = this.repository.maximumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.PROPOSED);
		maximumBudgetOfPatronagesProposedByCurrency.stream()
															.forEach( t -> maximumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.PROPOSED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> maximumBudgetOfPatronagesAcceptedByCurrency = this.repository.maximumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.ACCEPTED);
		maximumBudgetOfPatronagesAcceptedByCurrency.stream()
															.forEach( t -> maximumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.ACCEPTED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		final Collection<Tuple> maximumBudgetOfPatronagesDeniedByCurrency = this.repository.maximumBudgetOfPatronagesByStatusAndCurrency(PatronageStatus.DENIED);
		maximumBudgetOfPatronagesDeniedByCurrency.stream()
															.forEach( t -> maximumBudgetOfPatronagesByStatusAndCurrency
																.put(Pair.of(PatronageStatus.DENIED.toString(),t.get(0).toString()),Double.parseDouble(t.get(1).toString())));
		
		
		
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
