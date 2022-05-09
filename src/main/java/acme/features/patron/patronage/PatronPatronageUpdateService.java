/*
 * AuthenticatedEmployerUpdateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.datatypes.Money;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractUpdateService<Patron, Patronage> interface ---------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		final boolean result;
		Patronage patronage;
		int id;
		
		id = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(id);
		
		result = patronage.getDraftMode() && request.isPrincipal(patronage.getPatron());
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,"code","status","creationDate","startDate","endDate", "budget", "legalStuff", "link");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code","status","creationDate","startDate","endDate", "budget", "legalStuff", "link");
		model.setAttribute("inventorFullName", entity.getInventor().getIdentity().getFullName());
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());
		model.setAttribute("inventorUsername",entity.getInventor().getUserAccount().getUsername());
		model.setAttribute("update", true);
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
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
			errors.state(request, existing == null || existing.getId() == entity.getId() , "code", "patron.patronage.form.error.duplicated");
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
			
			budget = entity.getBudget();
		
			errors.state(request, budget.getAmount() >= 0,"budget", "patron.patronage.form.error.minimum-budget");
		}
		
		if (!errors.hasErrors("inventorUsername")) {
			Inventor inventor;
			
			inventor = this.repository.findOneInventorByUsername(request.getModel().getString("inventorUsername"));
			errors.state(request, inventor != null, "inventorUsername", "patron.patronage.form.error.inventorUsername");
		}
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		Inventor inventor;
		inventor = this.repository.findOneInventorByUsername(request.getModel().getString("inventorUsername"));
		
		entity.setInventor(inventor);
		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Patronage> request, final Response<Patronage> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}


}