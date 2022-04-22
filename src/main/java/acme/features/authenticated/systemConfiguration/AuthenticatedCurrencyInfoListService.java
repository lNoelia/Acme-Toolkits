package acme.features.authenticated.systemConfiguration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedCurrencyInfoListService implements AbstractListService<Authenticated, SystemConfiguration>{

	@Autowired
	protected AuthenticatedCurrencyInfoRepository repository;
	
	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<SystemConfiguration> findMany(final Request<SystemConfiguration> request) {
		assert request != null;

		Collection<SystemConfiguration> result;
		result = this.repository.findSystemConfiguration();
		return result;
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "acceptedCurrencies", "currentCurrency");
		model.setAttribute("acceptedCurrencies", entity.getAcceptedCurrencies());
		model.setAttribute("currentCurrency", entity.getSystemCurrency());
	}

}
