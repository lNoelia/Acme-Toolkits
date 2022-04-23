
package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result;
		final int totalNumberOfComponents;
		final Map<Pair<String, String>, Double> averageRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> deviationRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> minimumRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> maximumRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();

		final int totalNumberOfTools;
		final Map<String, Double> averageRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> deviationRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> minimumRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Map<String, Double> maximumRetailPriceOfToolsByCurrency = new HashMap<String, Double>();

		final Map<String, Integer> totalNumberOfPatronagesByStatus = new HashMap<String, Integer>();
		final Map<Pair<String, String>, Double> averageBudgetOfPatronagesByStatusAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> deviationBudgetOfPatronagesByStatusAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> minimumBudgetOfPatronagesByStatusAndCurrency = new HashMap<Pair<String, String>, Double>();
		final Map<Pair<String, String>, Double> maximumBudgetOfPatronagesByStatusAndCurrency = new HashMap<Pair<String, String>, Double>();

		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency().stream().forEach(x -> averageRetailPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(x.get(0).toString(), x.get(1).toString()), (double) Math.round(Double.parseDouble(x.get(2).toString()) *100)/100 ) );
		this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency().stream().forEach(x -> deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(x.get(0).toString(), x.get(1).toString()), (double) Math.round(Double.parseDouble(x.get(2).toString()) *100)/100 ) );
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency().stream().forEach(x -> minimumRetailPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(x.get(0).toString(), x.get(1).toString()), (double) Math.round(Double.parseDouble(x.get(2).toString()) *100)/100 ) );
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency().stream().forEach(x -> maximumRetailPriceOfComponentsByTechnologyAndCurrency.put(Pair.of(x.get(0).toString(), x.get(1).toString()), (double) Math.round(Double.parseDouble(x.get(2).toString()) *100)/100 ) );

		totalNumberOfTools = this.repository.totalNumberOfTools();
		this.repository.averageRetailPriceOfToolsByCurrency().stream().forEach(x -> averageRetailPriceOfToolsByCurrency.put(x.get(0).toString(), (double) Math.round(Double.parseDouble(x.get(1).toString()) *100)/100 ));
		this.repository.deviationRetailPriceOfToolsByCurrency().stream().forEach(x -> deviationRetailPriceOfToolsByCurrency.put(x.get(0).toString(), (double) Math.round(Double.parseDouble(x.get(1).toString()) *100)/100 ));
		this.repository.minimumRetailPriceOfToolsByCurrency().stream().forEach(x -> minimumRetailPriceOfToolsByCurrency.put(x.get(0).toString(), (double) Math.round(Double.parseDouble(x.get(1).toString()) *100)/100 ));
		this.repository.maximumRetailPriceOfToolsByCurrency().stream().forEach(x -> maximumRetailPriceOfToolsByCurrency.put(x.get(0).toString(), (double) Math.round(Double.parseDouble(x.get(1).toString()) *100)/100 ));
		
		this.repository.totalNumberOfPatronagesByStatus().stream().forEach(x -> totalNumberOfPatronagesByStatus.put(x.get(0).toString(), Integer.parseInt(x.get(1).toString())));
		this.repository.averageBudgetOfPatronagesByStatusAndCurrency().stream().forEach(x -> averageBudgetOfPatronagesByStatusAndCurrency.put(Pair.of(x.get(0).toString(), x.get(1).toString()), (double) Math.round(Double.parseDouble(x.get(2).toString()) *100)/100 ) );
		this.repository.deviationBudgetOfPatronagesByStatusAndCurrency().stream().forEach(x -> deviationBudgetOfPatronagesByStatusAndCurrency.put(Pair.of(x.get(0).toString(), x.get(1).toString()), (double) Math.round(Double.parseDouble(x.get(2).toString()) *100)/100 ) );
		this.repository.minimumBudgetOfPatronagesByStatusAndCurrency().stream().forEach(x -> minimumBudgetOfPatronagesByStatusAndCurrency.put(Pair.of(x.get(0).toString(), x.get(1).toString()), (double) Math.round(Double.parseDouble(x.get(2).toString()) *100)/100 ) );
		this.repository.maximumBudgetOfPatronagesByStatusAndCurrency().stream().forEach(x -> maximumBudgetOfPatronagesByStatusAndCurrency.put(Pair.of(x.get(0).toString(), x.get(1).toString()), (double) Math.round(Double.parseDouble(x.get(2).toString()) *100)/100 ) );

		result = new AdministratorDashboard();
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setAverageRetailPriceOfComponentsByTechnologyAndCurrency(averageRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setDeviationRetailPriceOfComponentsByTechnologyAndCurrency(deviationRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setMinimumRetailPriceOfComponentsByTechnologyAndCurrency(minimumRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setMaximumRetailPriceOfComponentsByTechnologyAndCurrency(maximumRetailPriceOfComponentsByTechnologyAndCurrency);

		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setAverageRetailPriceOfToolsByCurrency(averageRetailPriceOfToolsByCurrency);
		result.setDeviationRetailPriceOfToolsByCurrency(deviationRetailPriceOfToolsByCurrency);
		result.setMinimumRetailPriceOfToolsByCurrency(minimumRetailPriceOfToolsByCurrency);
		result.setMaximumRetailPriceOfToolsByCurrency(maximumRetailPriceOfToolsByCurrency);

		result.setTotalNumberOfPatronagesByStatus(totalNumberOfPatronagesByStatus);
		result.setAverageBudgetOfPatronagesByStatusAndCurrency(averageBudgetOfPatronagesByStatusAndCurrency);
		result.setDeviationBudgetOfPatronagesByStatusAndCurrency(deviationBudgetOfPatronagesByStatusAndCurrency);
		result.setMinimumBudgetOfPatronagesByStatusAndCurrency(minimumBudgetOfPatronagesByStatusAndCurrency);
		result.setMaximumBudgetOfPatronagesByStatusAndCurrency(maximumBudgetOfPatronagesByStatusAndCurrency);

		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfComponents", "averageRetailPriceOfComponentsByTechnologyAndCurrency", "deviationRetailPriceOfComponentsByTechnologyAndCurrency", "minimumRetailPriceOfComponentsByTechnologyAndCurrency",
			"maximumRetailPriceOfComponentsByTechnologyAndCurrency", "totalNumberOfTools", "averageRetailPriceOfToolsByCurrency", "deviationRetailPriceOfToolsByCurrency", "minimumRetailPriceOfToolsByCurrency", "maximumRetailPriceOfToolsByCurrency",
			"totalNumberOfPatronagesByStatus", "averageBudgetOfPatronagesByStatusAndCurrency", "deviationBudgetOfPatronagesByStatusAndCurrency", "minimumBudgetOfPatronagesByStatusAndCurrency", "maximumBudgetOfPatronagesByStatusAndCurrency");

	}

}
