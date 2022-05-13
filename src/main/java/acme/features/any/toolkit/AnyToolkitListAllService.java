package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListAllService implements AbstractListService<Any, Toolkit> {

	@Autowired
	protected AnyToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		Collection<Toolkit> result;
		result = this.repository.findPublishedToolkits();
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final StringBuilder builder = new StringBuilder();
		final int toolkitId = entity.getId();
		final Collection<Artefact> toolkitArtefacts = this.repository.findArtefactsByToolkitId(toolkitId);
		
		request.unbind(entity, model, "code", "title", "description");
		
		for (final Artefact a:toolkitArtefacts) {
			builder.append(String.format("%s; %s; %s; %s; ", 
				a.getName(),
				a.getDescription(),
				a.getCode(),
				a.getTechnology()));
		}
		final String payload = builder.toString();
		model.setAttribute("payload", payload);
	}

}
