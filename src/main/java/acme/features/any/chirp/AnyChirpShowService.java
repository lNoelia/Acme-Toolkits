package acme.features.any.chirp;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirps.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyChirpShowService implements AbstractShowService<Any, Chirp>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpRepository repository;

	// AbstractShowService<Any, Chirp> interface --------------

	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		boolean result;
		Calendar calendar;
		Date deadline;
		Chirp chirp;
		int id;
		
		
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline = calendar.getTime();
		
		id = request.getModel().getInteger("id");
		chirp = this.repository.findOneChirpById(id);
		result = chirp.getCreationDate().after(deadline);

		return result;
	}

	@Override
	public Chirp findOne(final Request<Chirp> request) {
		assert request != null;

		Chirp result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneChirpById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "author", "body", "email");
	}


}
