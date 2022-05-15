package acme.features.any.worksIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyWorksInListByToolkitService implements AbstractListService<Any, WorksIn> {

	@Autowired
	protected AnyWorksInRepository repository;
	
	@Override
	public boolean authorise(final Request<WorksIn> request) {
		assert request != null;
		
		int toolkitId;
		Toolkit toolkit;
		
		toolkitId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(toolkitId);
		
		return toolkit.isPublished();
	}
	
	
	@Override
	public Collection<WorksIn> findMany(final Request<WorksIn> request) {
		assert request != null;

		int toolkitId;		
		Collection<WorksIn> result;

		toolkitId = request.getModel().getInteger("toolkitId");
		result = this.repository.findWorksInsByToolkitId(toolkitId);

		return result;
	}


	@Override
	public void unbind(final Request<WorksIn> request, final WorksIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount", "artefact.type", "artefact.name", "artefact.code");	
	}



}
