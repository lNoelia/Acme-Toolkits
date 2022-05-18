package acme.features.inventor.worksIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.ArtefactType;
import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorWorksInUpdateService implements AbstractUpdateService<Inventor, WorksIn>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorWorksInRepository repository;

	// AbstractUpdateService<Inventor, WorksIn> -------------------------------------


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
	public void validate(final Request<WorksIn> request, final WorksIn entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("amount")) {
			
			boolean isAComponent;
			isAComponent = entity.getArtefact().getType().equals(ArtefactType.COMPONENT);
			
			boolean isATool;
			isATool = entity.getArtefact().getType().equals(ArtefactType.TOOL);

			errors.state(request, isAComponent || isATool&&(entity.getAmount()==1), "amount", "inventor.worksin.form.error.one-tool");
		}

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
		model.setAttribute("readonly", entity.getToolkit().isPublished() && (entity.getArtefact().getType().equals(ArtefactType.TOOL)));
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
	public void update(final Request<WorksIn> request, final WorksIn entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
