package acme.features.inventor.worksIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorWorksInShowService implements AbstractShowService<Inventor, WorksIn>{
	

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorWorksInRepository repository;

	// AbstractListService<Inventor, WorksIn> interface --------------


	@Override
	public boolean authorise(final Request<WorksIn> request) {
		assert request != null;

		int worksInId;
		int activeRolId;
		WorksIn worksIn;
		
		worksInId = request.getModel().getInteger("id");
		activeRolId = request.getPrincipal().getActiveRoleId();
		worksIn = this.repository.findOneWorksInById(worksInId);
		
		return activeRolId == worksIn.getToolkit().getInventor().getId();
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
	public void unbind(final Request<WorksIn> request, final WorksIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount", "artefact.name", "artefact.type","artefact.code", "artefact.retailPrice","artefact.technology", "artefact.description", "artefact.link");				
	}
}