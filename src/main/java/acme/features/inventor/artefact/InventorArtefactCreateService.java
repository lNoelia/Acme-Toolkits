package acme.features.inventor.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.artefact.Artefact;
import acme.entities.artefact.ArtefactType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.utils.SpamDetector;

@Service
public class InventorArtefactCreateService implements AbstractCreateService<Inventor , Artefact>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorArtefactRepository repository;

		// AbstractCreateService<Inventor, Artefact> interface -------------------------


		@Override
		public boolean authorise(final Request<Artefact> request) {
			assert request != null;

			return true;
		}

		@Override
		public Artefact instantiate(final Request<Artefact> request) {
			assert request != null;

			Artefact result;
			final Inventor inventor;

			inventor = this.repository.findOneInventorById(request.getPrincipal().getActiveRoleId());
			result = new Artefact();
			result.setPublished(false);
			result.setInventor(inventor);

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
				errors.state(request, existing == null, "code", "inventor.artefact.form.error.duplicated");
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
			
			boolean spam;
			SpamDetector.readData();
			spam = SpamDetector.check(entity.getName())
				|| SpamDetector.check(entity.getDescription())
				|| SpamDetector.check(entity.getTechnology());
			errors.state(request, !spam, "spam", "inventor.artefact.spam");
		}

		@Override
		public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "code", "name", "technology", "description", "retailPrice", "link", "type", "published");
		}

		@Override
		public void create(final Request<Artefact> request, final Artefact entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}
	
}
