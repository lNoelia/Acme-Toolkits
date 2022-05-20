package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.moneyExchange.MoneyExchangeService;
import acme.entities.toolkits.Toolkit;
import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListMineService implements AbstractListService<Inventor, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	@Autowired
	protected MoneyExchangeService moneyExchangeService;
	
	// AbstractListService<Inventor, Toolkit> interface --------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		Collection<Toolkit> result;
		int inventorId;

		inventorId = request.getPrincipal().getActiveRoleId();
		
		result = this.repository.findAllToolkitByInventorId(inventorId);

		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final int toolkitId = entity.getId();
		final Collection<WorksIn> toolkitWorksIns = this.repository.findWorksInsByToolkitId(toolkitId);
		
		entity.calculatePrice(toolkitWorksIns, this.moneyExchangeService);
		
		request.unbind(entity, model, "code", "title", "price");
	}

}
