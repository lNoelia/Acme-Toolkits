package acme.features.inventor.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorArtefactPublishService implements AbstractUpdateService<Inventor, Artefact>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorArtefactRepository repository;

		// AbstractUpdateService<Inventor, Artefact> interface ---------------------------


		@Override
		public boolean authorise(final Request<Artefact> request) {
			assert request != null;

			boolean result;
			int artefactId;
			Artefact artefact;
			Inventor inventor;

			artefactId = request.getModel().getInteger("id");
			artefact = this.repository.findOneArtefactById(artefactId);
			inventor = artefact.getInventor();
			result = !artefact.isPublished() && request.isPrincipal(inventor);

			return result;
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
		public void bind(final Request<Artefact> request, final Artefact entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link", "type");
		}

		@Override
		public void validate(final Request<Artefact> request, final Artefact entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			if (!errors.hasErrors("code")) {
				Artefact existing;

				existing = this.repository.findOneArtefactByCode(entity.getCode());
				errors.state(request, existing == null || existing.getId() == entity.getId() , "code", "inventor.artefact.form.error.duplicated");
			}

			if (!errors.hasErrors("retailPrice")) {
				Money price;
				price = entity.getRetailPrice();
				if(price!=null) {
					final Double amount = price.getAmount();
					errors.state(request, (amount>0 && entity.getType()==ArtefactType.COMPONENT)||(amount>=0 && entity.getType()==ArtefactType.TOOL), "retailPrice", "inventor.artefact.form.error.negative-price");	
				}else {
					errors.state(request, (price!=null), "retailPrice", "inventor.artefact.form.error.no-price");
				}
			}
		}

		@Override
		public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "link", "type", "published");
		}

		@Override
		public void update(final Request<Artefact> request, final Artefact entity) {
			assert request != null;
			assert entity != null;

			entity.setPublished(true);
			this.repository.save(entity);
		}

}
