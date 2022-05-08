package acme.features.inventor.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorArtefactDeleteService implements AbstractDeleteService<Inventor, Artefact>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorArtefactRepository repository;

			// AbstractUpdateService<Inventor, Artefact> -------------------------------------


		@Override	
		public boolean authorise(final Request<Artefact> request) {
				assert request != null;

				boolean result;
				int masterId;
				Artefact artefact;
				Inventor inventor;

				masterId = request.getModel().getInteger("id");
				artefact = this.repository.findOneArtefactById(masterId);
				inventor = artefact.getInventor();
				result = !artefact.isPublished() && request.isPrincipal(inventor);

				return result;
			}

	@Override
	public void bind(final Request<Artefact> request, final Artefact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link", "type");
	}
	
	@Override
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "link", "type", "published");
	}

	@Override
	public Artefact findOne(final Request<Artefact> request) {
		assert request != null;

		Artefact result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneArtefactById(id);

		return result;
	}

	@Override
	public void validate(final Request<Artefact> request, final Artefact entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Artefact> request, final Artefact entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
