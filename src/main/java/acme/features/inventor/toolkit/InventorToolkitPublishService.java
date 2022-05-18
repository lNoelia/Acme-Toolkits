package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.utils.SpamDetector;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractUpdateService<Inventor, Toolkit> interface ---------------------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;
		Inventor inventor;

		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(masterId);
		inventor = toolkit.getInventor();
		result = !toolkit.isPublished() && request.isPrincipal(inventor);

		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,  "title", "code", "price", "description", "assemblyNotes", "link");
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId() , "code", "inventor.toolkit.form.error.duplicated");
		}
		
		int masterId;
		masterId = request.getModel().getInteger("id");
		Collection<Artefact> artefacts;
		artefacts = this.repository.findOneToolkitArtefacts(masterId);
		errors.state(request, !artefacts.isEmpty(), "no-artefacts", "inventor.toolkit.form.error.no-artefacts");
		
		boolean spam;
		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		SpamDetector.readData(sc.getStrongSpamWords(), sc.getWeakSpamWords(), 
							  sc.getStrongSpamThreshold(), sc.getWeakSpamThreshold());
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
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		this.repository.save(entity);
	}

}
