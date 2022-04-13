package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit>{
	

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractListService<Inventor, Toolkit> interface --------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;
		int inventorId;
		
		id = request.getModel().getInteger("id");
		inventorId = request.getPrincipal().getActiveRoleId();

		
		result = this.repository.findOneToolkitById(id, inventorId);

		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "code", "price", "description", "assemblyNotes", "link");				
	}
}