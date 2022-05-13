package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.utils.SpamDetector;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractCreateService<Inventor, Toolkit> interface -------------------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		final Inventor inventor;

		inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
		result = new Toolkit();
		result.setPublished(false);
		result.setInventor(inventor);	

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "code", "price", "description", "assemblyNotes", "link");
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.toolkit.form.error.duplicated");
		}
		
		boolean spam;
		SpamDetector.readData();
		spam = SpamDetector.check(entity.getTitle())
			|| SpamDetector.check(entity.getDescription())
			|| SpamDetector.check(entity.getAssemblyNotes());
		errors.state(request, !spam, "spam", "inventor.toolkit.spam");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "code", "price", "description", "assemblyNotes", "link", "published");
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
	
}
