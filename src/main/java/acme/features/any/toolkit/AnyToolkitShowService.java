package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.moneyExchange.MoneyExchangeService;
import acme.entities.toolkits.Toolkit;
import acme.entities.worksIn.WorksIn;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any,Toolkit>{

	// Internal state ---------------------------------------------------------
	@Autowired
	protected AnyToolkitRepository repository;
	
	@Autowired
	protected MoneyExchangeService moneyExchangeService;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit toolkit;
		int toolkitId;
		
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(toolkitId);
		
		return toolkit.isPublished();
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int toolkitId;
		Collection<WorksIn> worksIns;
		
		toolkitId = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(toolkitId);
		
		worksIns = this.repository.findWorksInsByToolkitId(toolkitId);
		result.calculatePrice(worksIns, this.moneyExchangeService);
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "published", "price");
	}

}
