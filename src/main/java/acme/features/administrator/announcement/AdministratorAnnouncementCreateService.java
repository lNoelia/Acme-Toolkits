package acme.features.administrator.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.utils.SpamDetector;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	@Autowired
	protected AdministratorAnnouncementRepository repository;

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "body", "critical", "link");
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "critical", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		Date creationDate;
		
		creationDate = new Date(System.currentTimeMillis() - 1);

		result = new Announcement();
		result.setCreationDate(creationDate);
		result.setTitle("");
		result.setBody("");
		result.setCritical(false);
		result.setLink("");

		return result;
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;
		boolean spam;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		SpamDetector.readData();
		spam = SpamDetector.check(entity.getTitle())
			|| SpamDetector.check(entity.getBody());
		errors.state(request, !spam, "spam", "administrator.announcement.spam");
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);
		this.repository.save(entity);
	}
	
	
}
