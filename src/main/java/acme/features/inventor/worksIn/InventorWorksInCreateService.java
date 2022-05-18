package acme.features.inventor.worksIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.entities.toolkits.Toolkit;
import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorWorksInCreateService implements AbstractCreateService<Inventor, WorksIn>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorWorksInRepository repository;

	// AbstractCreateService<Inventor, WorksIn> interface -------------------------


	@Override
	public boolean authorise(final Request<WorksIn> request) {
		assert request != null;
		
		int activeRolId;
		int masterId;
		Toolkit toolkit;
		
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		activeRolId = request.getPrincipal().getActiveRoleId();
	
		return !toolkit.isPublished() && (toolkit.getInventor().getId()==activeRolId);
	}

	@Override
	public WorksIn instantiate(final Request<WorksIn> request) {
		assert request != null;

		WorksIn result;
		int masterId;
		Toolkit toolkit;
		
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		
		result = new WorksIn();
		result.setToolkit(toolkit);

		return result;
	}

	@Override
	public void bind(final Request<WorksIn> request, final WorksIn entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Artefact artefact;

		artefact = this.repository.findOneArtefactById(request.getModel().getInteger("artefactId"));
		entity.setArtefact(artefact);

		request.bind(entity, errors, "amount");

	}

	@Override
	public void validate(final Request<WorksIn> request, final WorksIn entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(!errors.hasErrors("artefacts")) {

			Collection<Integer> artefactsByToolkit;
			artefactsByToolkit = this.repository.findArtefactsIdByToolkit(entity.getToolkit().getId());

			boolean artefactIsPublished;
			artefactIsPublished = entity.getArtefact().isPublished();
			
			boolean artefactIsNotInToolkit;
			artefactIsNotInToolkit = !artefactsByToolkit.contains(entity.getArtefact().getId());
			
			errors.state(request, artefactIsPublished || artefactIsNotInToolkit, "artefacts", "inventor.worksin.form.error.not-available");
		}
		if(!errors.hasErrors("amount")) {
			
			boolean isAComponent;
			isAComponent = entity.getArtefact().getType().equals(ArtefactType.COMPONENT);
			
			boolean isATool;
			isATool = entity.getArtefact().getType().equals(ArtefactType.TOOL);

			errors.state(request, isAComponent || isATool&&(entity.getAmount()==1), "amount", "inventor.worksin.form.error.one-tool");
		}
	}

	@Override
	public void unbind(final Request<WorksIn> request, final WorksIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		int masterId;	
		
		masterId = request.getModel().getInteger("masterId");
		Collection<Artefact> artefacts;
		artefacts = this.repository.findArtefactsForToolkit(masterId);

		request.unbind(entity, model, "amount");
		model.setAttribute("artefacts", artefacts);
		model.setAttribute("masterId", masterId);
	}

	@Override
	public void create(final Request<WorksIn> request, final WorksIn entity) {
		assert request != null;
		assert entity != null;
		
		
		this.repository.save(entity);
	}
	
	
}
