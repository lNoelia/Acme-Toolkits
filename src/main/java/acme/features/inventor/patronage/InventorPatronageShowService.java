package acme.features.inventor.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.moneyExchange.MoneyExchangeService;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Autowired
	protected MoneyExchangeService moneyExchangeService;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		Integer masterId;
		Patronage patronage;
		Date currentMoment;

		masterId = request.getModel().getInteger("id");
		patronage = this.repository.findPatronageById(masterId);
		currentMoment = new Date();
		result = patronage.getEndDate().after(currentMoment);
		
		final Principal principal = request.getPrincipal();	
		return result && 
				principal.hasRole(Inventor.class) && 
				principal.getActiveRoleId()==patronage.getInventor().getId();
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Money convertedPrice;
		Money budget;
		
		budget = entity.getBudget();
		convertedPrice = this.moneyExchangeService.convertToSystemCurrency(budget);
		
		final Integer patronId = entity.getPatron().getId();
		final Patron patron = this.repository.findPatronByPatronId(patronId);
		
		request.unbind(entity, model, "code", "status", "budget", "legalStuff", "link", "creationDate", "startDate", "endDate");
		model.setAttribute("patron", patron.getCompany());
		model.setAttribute("readonly", true);
		model.setAttribute("canBeUpdated", entity.getStatus()==PatronageStatus.PROPOSED);
		model.setAttribute("convertedPrice", convertedPrice);
	}
}
