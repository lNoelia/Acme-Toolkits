package acme.features.inventor.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorArtefactPerToolkitListService implements AbstractListService<Inventor, Artefact>{

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
	public Collection<Artefact> findMany(final Request<Artefact> request) {
		assert request != null;

		Collection<Artefact> result;
		int masterId;
		int inventorId;

		masterId = request.getModel().getInteger("masterId");
		inventorId = request.getPrincipal().getActiveRoleId();
		
		result = this.repository.findAllArtefactByInventorPerToolkitId(masterId, inventorId);

		return result;
	}

	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "technology", "retailPrice", "type");	
		
		final ArtefactType type = model.getAttribute("type", ArtefactType.class);
		String typex = type.toString();
		final String lowercase = typex.toLowerCase();
		final String t = typex.substring(0,1);
		typex = t.concat(lowercase.substring(1));
		model.setAttribute("type", typex);
	}

}
