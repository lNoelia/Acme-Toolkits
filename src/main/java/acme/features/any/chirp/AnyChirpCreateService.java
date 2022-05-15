package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirps.Chirp;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import acme.utils.SpamDetector;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp>  {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpRepository repository;

	// AbstractListService<Any, Chirp> interface --------------

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "author", "body", "email");
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "author", "body", "email");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
	}
	
	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;

		Chirp result;
		Date creationDate;
		
		creationDate = new Date(System.currentTimeMillis() - 1);

		result = new Chirp();
		result.setCreationDate(creationDate);
		result.setTitle("");
		result.setBody("");
		result.setAuthor("");
		result.setEmail("");

		return result;
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;
		boolean spam;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	
		final SystemConfiguration sc = this.repository.findSystemConfiguration();
		SpamDetector.readData(sc.getStrongSpamWords(), sc.getWeakSpamWords(), 
							  sc.getStrongSpamThreshold(), sc.getWeakSpamThreshold());
		spam = SpamDetector.check(entity.getTitle())
			|| SpamDetector.check(entity.getAuthor())
			|| SpamDetector.check(entity.getBody());
		errors.state(request, !spam, "spam", "any.chirp.spam");
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);
		this.repository.save(entity);
	}
	
	

}
