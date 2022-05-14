package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.moneyExchange.MoneyExchangeService;
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
	
	@Autowired
	protected MoneyExchangeService moneyExchangeService;

	// AbstractListService<Inventor, Toolkit> interface --------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		int toolkitId;
		int activeRolId;
		Toolkit toolkit;
		
		toolkitId = request.getModel().getInteger("id");
		activeRolId = request.getPrincipal().getActiveRoleId();
		toolkit = this.repository.findOneToolkitById(toolkitId);
		
		return activeRolId == toolkit.getInventor().getId();
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;
		
		id = request.getModel().getInteger("id");
		
		result = this.repository.findOneToolkitById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		//Money convertedPrice;
		//Money price;
		
		//price = entity.getPrice();
		//convertedPrice = this.moneyExchangeService.convertToSystemCurrency(price);

		request.unbind(entity, model, "title", "code", "price", "description", "assemblyNotes", "link", "published");
		//model.setAttribute("convertedPrice", convertedPrice);
	}
}