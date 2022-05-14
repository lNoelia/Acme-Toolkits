package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractCreateService<Patron,Patronage> interface --------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		Principal principal;
		Patron patron;
		
		principal = request.getPrincipal();
		patron = this.repository.findOnePatronByUserId(principal.getAccountId());

		result = new Patronage();
		result.setPatron(patron);
		result.setDraftMode(true);
		

		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,"creationDate","status","code", "legalStuff", "budget", "link","startDate","endDate");
		entity.setDraftMode(true);
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			String code;
			Patronage existing;
			code = entity.getCode();
			existing = this.repository.findOnePatronageByCode(code);
			errors.state(request, existing == null  , "code", "patron.patronage.form.error.duplicated");
		}
		
		if (!errors.hasErrors("startDate")) {
	
			Date creationDate;
			Date minimumDeadline;
			Calendar calendar;
			
			creationDate=entity.getCreationDate();
			calendar = new GregorianCalendar();
			calendar.setTime(creationDate);
			calendar.add(Calendar.MONTH, 1);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getStartDate().after(minimumDeadline), "startDate", "patron.patronage.form.error.too-close-startDate");
		}
		
		if (!errors.hasErrors("endDate")) {
			
			Date startDate;
			Date minimumDeadline;
			Calendar calendar;
			
			startDate=entity.getStartDate();
			calendar = new GregorianCalendar();
			calendar.setTime(startDate);
			calendar.add(Calendar.MONTH, 1);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getEndDate().after(minimumDeadline), "endDate", "patron.patronage.form.error.too-close-endDate");
		}
		
		if (!errors.hasErrors("budget")) {
            Money budget;
            String acceptedCurrencies;
            
            budget = entity.getBudget();
            acceptedCurrencies=this.repository.findAcceptedCurrencies();

            errors.state(request, budget.getAmount() >= 0,"budget", "patron.patronage.form.error.minimum-budget");
            errors.state(request,acceptedCurrencies.contains(budget.getCurrency()), "budget","patron.patronage.form.error.not-accepted-currency");
        }


	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Date creationDate;
		Collection<Inventor> inventors;
		inventors=this.repository.findAllInventors();
		
		creationDate = new Date(System.currentTimeMillis() - 1);
		
		entity.setStatus(PatronageStatus.PROPOSED);
		entity.setCreationDate(creationDate);
		request.unbind(entity, model,"creationDate","code", "legalStuff", "budget", "link","startDate","endDate","status");
		model.setAttribute("create",true);
		model.setAttribute("inventors", inventors);
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;
		Inventor inventor;
		
		inventor = this.repository.findInventorById(request.getModel().getInteger("inventorId"));
		creationDate = new Date(System.currentTimeMillis() - 1);
		
		entity.setInventor(inventor);
		entity.setCreationDate(creationDate);
		this.repository.save(entity);
	}

}
