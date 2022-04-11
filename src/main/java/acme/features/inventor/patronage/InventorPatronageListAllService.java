package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class InventorPatronageListAllService implements AbstractListService<Inventor, Patronage> {

	@Autowired
	protected InventorPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		final Principal principal = request.getPrincipal();
		return principal.hasRole(Inventor.class);
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request != null;

		Collection<Patronage> result;

		result = this.repository.findManyPatronagesByAvailability();

		return result;
	}
	
	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final Integer patronId = entity.getPatron().getId();
		final Patron patron = this.repository.findPatronByPatronId(patronId);
		request.unbind(entity, model, "code", "status", "budget");
		model.setAttribute("patron", patron.getCompany());
	}
}
