package acme.features.inventor.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorArtefactShowService implements AbstractShowService<Inventor, Artefact>{


	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorArtefactRepository repository;

	// AbstractListService<Inventor, Artefact> interface --------------


	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;

		return true;
	}

	@Override
	public Artefact findOne(final Request<Artefact> request) {
		assert request != null;

		Artefact result;
		int id;
		int inventorId;
		
		id = request.getModel().getInteger("id");
		inventorId = request.getPrincipal().getActiveRoleId();

		
		result = this.repository.findOneArtefactById(id, inventorId );

		return result;
	}
	
	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "type");		
	}
}
