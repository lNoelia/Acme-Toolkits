package acme.features.inventor.worksIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorWorksInDeleteService implements AbstractDeleteService<Inventor, WorksIn>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorWorksInRepository repository;

	// AbstractDeleteService<Inventor, WorksIn> -------------------------------------


	@Override	
	public boolean authorise(final Request<WorksIn> request) {
		assert request != null;

		int activeRolId;
		int masterId;
		WorksIn worksIn;
		
		masterId = request.getModel().getInteger("id");
		worksIn = this.repository.findOneWorksInById(masterId);
		activeRolId = request.getPrincipal().getActiveRoleId();
	
		return !worksIn.getToolkit().isPublished() && (worksIn.getToolkit().getInventor().getId()==activeRolId);
	}

	@Override
	public void bind(final Request<WorksIn> request, final WorksIn entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "amount");
	}
	
	@Override
	public void unbind(final Request<WorksIn> request, final WorksIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount", "artefact.name", "artefact.type","artefact.code", "artefact.retailPrice","artefact.technology", "artefact.description", "artefact.link");
	}

	@Override
	public WorksIn findOne(final Request<WorksIn> request) {
		assert request != null;

		WorksIn result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorksInById(id);

		return result;
	}

	@Override
	public void validate(final Request<WorksIn> request, final WorksIn entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<WorksIn> request, final WorksIn entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}


}
