package acme.features.inventor.artefact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.moneyExchange.MoneyExchangeService;
import acme.entities.artefact.Artefact;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorArtefactShowService implements AbstractShowService<Inventor, Artefact>{


	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorArtefactRepository repository;
	
	@Autowired
	protected MoneyExchangeService moneyExchangeService;

	// AbstractListService<Inventor, Artefact> interface --------------


	@Override
	public boolean authorise(final Request<Artefact> request) {
		assert request != null;
		
		int artefactId;
		int activeRolId;
		Artefact artefact;
		
		artefactId = request.getModel().getInteger("id");
		artefact = this.repository.findOneArtefactById(artefactId);
		
		activeRolId = request.getPrincipal().getActiveRoleId();
		
		return activeRolId == artefact.getInventor().getId();
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
	public void unbind(final Request<Artefact> request, final Artefact entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Money convertedPrice;
		Money retailPrice;
				
		retailPrice = entity.getRetailPrice();
		convertedPrice = this.moneyExchangeService.convertToSystemCurrency(retailPrice);

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "type","published");
		model.setAttribute("convertedPrice", convertedPrice);
	}
}
