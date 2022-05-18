package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.moneyExchange.MoneyExchangeService;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected MoneyExchangeService moneyExchangeService;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		int patronageId;
		Patronage patronage;
		int activeRoleId;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		activeRoleId = request.getPrincipal().getActiveRoleId();

		return activeRoleId == patronage.getPatron().getId();
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		final Patronage result;
		int patronageId;
		
		patronageId = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(patronageId);
		
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Money convertedPrice;
		Money budget;
		Collection<Inventor> inventors;
		inventors=this.repository.findAllInventors();
				
		budget = entity.getBudget();
		convertedPrice = this.moneyExchangeService.convertToSystemCurrency(budget);
		
		request.unbind(entity, model, "draftMode","code", "status", "budget", "startDate", "endDate", "legalStuff", "link", "creationDate","inventor");
		model.setAttribute("inventorFullName", entity.getInventor().getIdentity().getFullName());
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("readonly", !entity.getDraftMode());
		model.setAttribute("inventors", inventors);
		model.setAttribute("convertedPrice", convertedPrice);
	}

}
