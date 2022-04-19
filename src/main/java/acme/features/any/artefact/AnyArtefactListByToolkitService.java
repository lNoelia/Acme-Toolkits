package acme.features.any.artefact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyArtefactListByToolkitService implements AbstractListService<Any, Artefact> {

	@Autowired
	protected AnyArtefactRepository repository;
	
	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;
		
		int toolkitId;
		Toolkit toolkit;
		
		toolkitId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(toolkitId);
		
		return toolkit.isPublished();
	}
	
	
	@Override
	public Collection<Artefact> findMany(final Request<Artefact> request) {
		assert request != null;

		int toolkitId;		
		Collection<Artefact> result;

		toolkitId = request.getModel().getInteger("toolkitId");
		result = this.repository.findArtefactsByToolkitId(toolkitId);

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
