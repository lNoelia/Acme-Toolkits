package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected PatronPatronageRepository repository;
	
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
		
		request.unbind(entity, model, "code", "status", "budget", "startDate", "endDate", "legalStuff", "link", "creationDate");
		model.setAttribute("inventor", entity.getInventor().getUserAccount().getUsername());
		
	}

}